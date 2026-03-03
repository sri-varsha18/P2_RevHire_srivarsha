package com.rev.app.revhire.service;

import com.rev.app.revhire.entity.Job;
import com.rev.app.revhire.entity.JobSeeker;
import com.rev.app.revhire.repository.JobRepository;
import com.rev.app.revhire.repository.JobSeekerRepository;
import com.rev.app.revhire.repository.JobSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private com.rev.app.revhire.service.NotificationService notificationService;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public List<Job> findJobs(String title, String location, Integer experience,
                              String companyName, String jobType, String salaryRange, LocalDate postedAfter) {
        Specification<Job> spec = JobSpecification.filterJobs(title, location, experience, companyName, jobType,
                salaryRange, postedAfter);
        return jobRepository.findAll(spec);
    }

    public List<Job> getAllOpenJobs() {
        return jobRepository.findByStatus("OPEN");
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> searchJobs(String title, String location, String jobType) {
        return jobRepository.searchJobs(title, location, jobType);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    @Transactional
    public Job createJob(Job job) {
        if (job.getStatus() == null) {
            job.setStatus("OPEN");
        }
        if (job.getCreatedAt() == null) {
            job.setCreatedAt(java.time.LocalDateTime.now());
        }
        Job saved = jobRepository.save(job);
        jobRepository.flush();

        // Job Recommendations: Notify seekers with matching skills
        try {
            List<JobSeeker> seekers = jobSeekerRepository.findAll();
            for (JobSeeker seeker : seekers) {
                if (seeker.getLocation() != null && seeker.getLocation().equalsIgnoreCase(job.getLocation())) {
                    notificationService.sendNotification(seeker.getUser(),
                            "New job recommendation: " + job.getTitle() + " at " + job.getCompany().getName());
                }
            }
        } catch (Exception e) {
            // Log but don't let notification failures roll back the job save
            System.err.println("Warning: Failed to send job notifications: " + e.getMessage());
        }

        return saved;
    }

    @Transactional
    public Job updateJob(Long id, Job jobDetails) {
        Job job = getJobById(id);
        job.setTitle(jobDetails.getTitle());
        job.setDescription(jobDetails.getDescription());
        job.setSkillsRequired(jobDetails.getSkillsRequired());
        job.setExperienceRequired(jobDetails.getExperienceRequired());
        job.setEducationRequired(jobDetails.getEducationRequired());
        job.setLocation(jobDetails.getLocation());
        job.setSalaryRange(jobDetails.getSalaryRange());
        job.setJobType(jobDetails.getJobType());
        job.setDeadline(jobDetails.getDeadline());
        job.setOpenings(jobDetails.getOpenings());
        if (jobDetails.getStatus() != null) {
            job.setStatus(jobDetails.getStatus());
        }
        return jobRepository.save(job);
    }

    @Transactional
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public List<Job> getJobsByCompany(Long companyId) {
        return jobRepository.findByCompany_CompanyId(companyId);
    }

    @Transactional
    public void closeJob(Long id) {
        Job job = getJobById(id);
        job.setStatus("CLOSED");
        jobRepository.save(job);
    }

    @Transactional
    public void reopenJob(Long id) {
        Job job = getJobById(id);
        job.setStatus("OPEN");
        jobRepository.save(job);
    }

    @Transactional
    public void markJobAsFilled(Long id) {
        Job job = getJobById(id);
        job.setStatus("FILLED");
        jobRepository.save(job);
    }
}
