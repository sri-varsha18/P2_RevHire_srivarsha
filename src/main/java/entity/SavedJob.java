package com.rev.app.revhire.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "saved_jobs")
public class SavedJob {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "saved_job_seq")
    @SequenceGenerator(name = "saved_job_seq", sequenceName = "SAVED_JOB_SEQ", allocationSize = 1)
    private Long savedJobId;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private com.rev.app.revhire.entity.JobSeeker jobSeeker;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    private LocalDateTime savedAt = LocalDateTime.now();

    public SavedJob() {
    }

    public SavedJob(Long savedJobId, com.rev.app.revhire.entity.JobSeeker jobSeeker, Job job, LocalDateTime savedAt) {
        this.savedJobId = savedJobId;
        this.jobSeeker = jobSeeker;
        this.job = job;
        this.savedAt = savedAt != null ? savedAt : LocalDateTime.now();
    }

    public static SavedJobBuilder builder() {
        return new SavedJobBuilder();
    }

    public Long getSavedJobId() {
        return savedJobId;
    }

    public void setSavedJobId(Long savedJobId) {
        this.savedJobId = savedJobId;
    }

    public com.rev.app.revhire.entity.JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(com.rev.app.revhire.entity.JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }

    public static class SavedJobBuilder {
        private Long savedJobId;
        private com.rev.app.revhire.entity.JobSeeker jobSeeker;
        private Job job;
        private LocalDateTime savedAt;

        public SavedJobBuilder savedJobId(Long savedJobId) {
            this.savedJobId = savedJobId;
            return this;
        }

        public SavedJobBuilder jobSeeker(com.rev.app.revhire.entity.JobSeeker jobSeeker) {
            this.jobSeeker = jobSeeker;
            return this;
        }

        public SavedJobBuilder job(Job job) {
            this.job = job;
            return this;
        }

        public SavedJobBuilder savedAt(LocalDateTime savedAt) {
            this.savedAt = savedAt;
            return this;
        }

        public SavedJob build() {
            return new SavedJob(savedJobId, jobSeeker, job, savedAt);
        }
    }
}
