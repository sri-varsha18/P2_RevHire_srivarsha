package com.rev.app.revhire.service;

import com.rev.app.revhire.entity.Employer;
import com.rev.app.revhire.entity.JobSeeker;
import com.rev.app.revhire.repository.EmployerRepository;
import com.rev.app.revhire.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private EmployerRepository employerRepository;

    public JobSeeker getJobSeekerProfile(Long seekerId) {
        return jobSeekerRepository.findById(seekerId).orElseThrow(() -> new RuntimeException("JobSeeker not found"));
    }

    public JobSeeker getJobSeekerById(Long id) {
        return getJobSeekerProfile(id);
    }

    public Employer getEmployerProfile(Long employerId) {
        return employerRepository.findById(employerId).orElseThrow(() -> new RuntimeException("Employer not found"));
    }

    public Employer getEmployerById(Long id) {
        return getEmployerProfile(id);
    }

    @Transactional
    public JobSeeker updateJobSeekerProfile(Long id, JobSeeker details) {
        JobSeeker seeker = getJobSeekerProfile(id);
        seeker.setName(details.getName());
        seeker.setPhone(details.getPhone());
        seeker.setLocation(details.getLocation());
        seeker.setEmploymentStatus(details.getEmploymentStatus());
        seeker.setSkills(details.getSkills());
        seeker.setEducation(details.getEducation());
        seeker.setExperience(details.getExperience());
        seeker.setCertifications(details.getCertifications());
        seeker.setProjects(details.getProjects());
        return jobSeekerRepository.save(seeker);
    }

    @Transactional
    public Employer updateEmployerProfile(Long id, Employer details) {
        Employer employer = getEmployerProfile(id);
        // Additional update fields can be added here
        return employerRepository.save(employer);
    }
}
