package com.rev.app.revhire.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_seq")
    @SequenceGenerator(name = "application_seq", sequenceName = "APPLICATION_SEQ", allocationSize = 1)
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private com.rev.app.revhire.entity.JobSeeker jobSeeker;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @Lob
    private String coverLetter;

    private String status = "APPLIED";
    private LocalDate appliedDate = LocalDate.now();

    @Lob
    private String withdrawReason;

    @Lob
    private String internalNotes;

    public Application() {
    }

    public Application(Long applicationId, Job job, com.rev.app.revhire.entity.JobSeeker jobSeeker, Resume resume, String coverLetter,
                       String status, LocalDate appliedDate, String withdrawReason, String internalNotes) {
        this.applicationId = applicationId;
        this.job = job;
        this.jobSeeker = jobSeeker;
        this.resume = resume;
        this.coverLetter = coverLetter;
        this.status = status != null ? status : "APPLIED";
        this.appliedDate = appliedDate != null ? appliedDate : LocalDate.now();
        this.withdrawReason = withdrawReason;
        this.internalNotes = internalNotes;
    }

    public static ApplicationBuilder builder() {
        return new ApplicationBuilder();
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public com.rev.app.revhire.entity.JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(com.rev.app.revhire.entity.JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getWithdrawReason() {
        return withdrawReason;
    }

    public void setWithdrawReason(String withdrawReason) {
        this.withdrawReason = withdrawReason;
    }

    public String getInternalNotes() {
        return internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }

    public static class ApplicationBuilder {
        private Long applicationId;
        private Job job;
        private com.rev.app.revhire.entity.JobSeeker jobSeeker;
        private Resume resume;
        private String coverLetter;
        private String status;
        private LocalDate appliedDate;
        private String withdrawReason;
        private String internalNotes;

        public ApplicationBuilder applicationId(Long applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public ApplicationBuilder job(Job job) {
            this.job = job;
            return this;
        }

        public ApplicationBuilder jobSeeker(com.rev.app.revhire.entity.JobSeeker jobSeeker) {
            this.jobSeeker = jobSeeker;
            return this;
        }

        public ApplicationBuilder resume(Resume resume) {
            this.resume = resume;
            return this;
        }

        public ApplicationBuilder coverLetter(String coverLetter) {
            this.coverLetter = coverLetter;
            return this;
        }

        public ApplicationBuilder status(String status) {
            this.status = status;
            return this;
        }

        public ApplicationBuilder appliedDate(LocalDate appliedDate) {
            this.appliedDate = appliedDate;
            return this;
        }

        public ApplicationBuilder withdrawReason(String withdrawReason) {
            this.withdrawReason = withdrawReason;
            return this;
        }

        public ApplicationBuilder internalNotes(String internalNotes) {
            this.internalNotes = internalNotes;
            return this;
        }

        public Application build() {
            return new Application(applicationId, job, jobSeeker, resume, coverLetter, status, appliedDate,
                    withdrawReason, internalNotes);
        }
    }
}
