package com.rev.app.revhire.dto;

import java.time.LocalDate;

public class ApplicationDto {
    private Long applicationId;
    private Long jobId;
    private String jobTitle;
    private Long jobSeekerId;
    private String jobSeekerName;
    private Long resumeId;
    private String coverLetter;
    private String status;
    private LocalDate appliedDate;
    private String withdrawReason;
    private String internalNotes;

    public ApplicationDto() {
    }

    public ApplicationDto(Long applicationId, Long jobId, String jobTitle, Long jobSeekerId, String jobSeekerName,
                          Long resumeId, String coverLetter, String status, LocalDate appliedDate, String withdrawReason,
                          String internalNotes) {
        this.applicationId = applicationId;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobSeekerId = jobSeekerId;
        this.jobSeekerName = jobSeekerName;
        this.resumeId = resumeId;
        this.coverLetter = coverLetter;
        this.status = status;
        this.appliedDate = appliedDate;
        this.withdrawReason = withdrawReason;
        this.internalNotes = internalNotes;
    }

    public static ApplicationDtoBuilder builder() {
        return new ApplicationDtoBuilder();
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(Long jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getJobSeekerName() {
        return jobSeekerName;
    }

    public void setJobSeekerName(String jobSeekerName) {
        this.jobSeekerName = jobSeekerName;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
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

    public static class ApplicationDtoBuilder {
        private Long applicationId;
        private Long jobId;
        private String jobTitle;
        private Long jobSeekerId;
        private String jobSeekerName;
        private Long resumeId;
        private String coverLetter;
        private String status;
        private LocalDate appliedDate;
        private String withdrawReason;
        private String internalNotes;

        public ApplicationDtoBuilder applicationId(Long applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public ApplicationDtoBuilder jobId(Long jobId) {
            this.jobId = jobId;
            return this;
        }

        public ApplicationDtoBuilder jobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public ApplicationDtoBuilder jobSeekerId(Long jobSeekerId) {
            this.jobSeekerId = jobSeekerId;
            return this;
        }

        public ApplicationDtoBuilder jobSeekerName(String jobSeekerName) {
            this.jobSeekerName = jobSeekerName;
            return this;
        }

        public ApplicationDtoBuilder resumeId(Long resumeId) {
            this.resumeId = resumeId;
            return this;
        }

        public ApplicationDtoBuilder coverLetter(String coverLetter) {
            this.coverLetter = coverLetter;
            return this;
        }

        public ApplicationDtoBuilder status(String status) {
            this.status = status;
            return this;
        }

        public ApplicationDtoBuilder appliedDate(LocalDate appliedDate) {
            this.appliedDate = appliedDate;
            return this;
        }

        public ApplicationDtoBuilder withdrawReason(String withdrawReason) {
            this.withdrawReason = withdrawReason;
            return this;
        }

        public ApplicationDtoBuilder internalNotes(String internalNotes) {
            this.internalNotes = internalNotes;
            return this;
        }

        public ApplicationDto build() {
            return new ApplicationDto(applicationId, jobId, jobTitle, jobSeekerId, jobSeekerName, resumeId, coverLetter,
                    status, appliedDate, withdrawReason, internalNotes);
        }
    }
}
