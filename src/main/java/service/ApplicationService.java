package com.rev.app.revhire.service;

import com.rev.app.revhire.entity.Application;
import com.rev.app.revhire.entity.Employer;
import com.rev.app.revhire.entity.Job;
import com.rev.app.revhire.entity.JobSeeker;
import com.rev.app.revhire.repository.ApplicationRepository;
import com.rev.app.revhire.repository.ApplicationSpecification;
import com.rev.app.revhire.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private com.rev.app.revhire.service.NotificationService notificationService;

    @Autowired
    private EmployerRepository employerRepository;

    public Application applyForJob(Application application) {
        if (applicationRepository.existsByJobAndJobSeeker(application.getJob(), application.getJobSeeker())) {
            throw new RuntimeException("You have already applied for this job");
        }
        application.setStatus("APPLIED");
        if (application.getAppliedDate() == null) {
            application.setAppliedDate(java.time.LocalDate.now());
        }
        Application saved = applicationRepository.save(application);
        applicationRepository.flush();

        // Notify Employer users of the company
        try {
            if (application.getJob().getCompany() != null) {
                List<Employer> employers = employerRepository
                        .findByCompany_CompanyId(application.getJob().getCompany().getCompanyId());
                for (Employer emp : employers) {
                    notificationService.sendNotification(emp.getUser(),
                            "New application received for " + application.getJob().getTitle() + " from "
                                    + application.getJobSeeker().getName());
                }
            }
        } catch (Exception e) {
            System.err.println("Warning: Failed to send application notifications: " + e.getMessage());
        }

        return saved;
    }

    @Transactional
    public void bulkUpdateStatus(List<Long> applicationIds, String status, String notes) {
        for (Long id : applicationIds) {
            updateApplicationStatus(id, status, notes);
        }
    }

    @Transactional
    public Application updateApplicationStatus(Long id, String status, String internalNotes) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application find error"));
        application.setStatus(status);
        if (internalNotes != null) {
            application.setInternalNotes(internalNotes);
        }

        Application saved = applicationRepository.save(application);
        applicationRepository.flush();

        // Notify Job Seeker
        try {
            notificationService.sendNotification(
                    application.getJobSeeker().getUser(),
                    "Your application for " + application.getJob().getTitle() + " is now " + status);
        } catch (Exception e) {
            System.err.println("Warning: Failed to send status update notification: " + e.getMessage());
        }

        return saved;
    }

    public List<Application> getApplicationsByJobSeeker(JobSeeker jobSeeker) {
        return applicationRepository.findByJobSeeker(jobSeeker);
    }

    public List<Application> getApplicationsByJob(Job job) {
        return applicationRepository.findByJob(job);
    }

    public List<Application> findApplications(Job job, String status, Integer minExperience, String skills) {
        return applicationRepository
                .findAll(ApplicationSpecification.filterApplications(job, status, minExperience, skills));
    }

    public List<Application> findApplications(Job job, String status, Integer minExperience, String skills,
                                              String education, java.time.LocalDate appliedAfter) {
        return applicationRepository
                .findAll(ApplicationSpecification.filterApplications(job, status, minExperience, skills, education,
                        appliedAfter));
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
    }

    public void withdrawApplication(Long id, String reason) {
        Application application = getApplicationById(id);
        application.setStatus("WITHDRAWN");
        application.setWithdrawReason(reason);
        applicationRepository.save(application);
    }

    @Transactional
    public void shortlistApplication(Long id, String notes) {
        updateApplicationStatus(id, "SHORTLISTED", notes);
    }

    @Transactional
    public void rejectApplication(Long id, String notes) {
        updateApplicationStatus(id, "REJECTED", notes);
    }
}
