package com.rev.app.revhire.dto;

import java.time.LocalDate;

public class JobDto {
    private Long jobId;
    private Long companyId;
    private String companyName;
    private String title;
    private String description;
    private String skillsRequired;
    private Integer experienceRequired;
    private String educationRequired;
    private String location;
    private String salaryRange;
    private String jobType;
    private LocalDate deadline;
    private Integer openings;
    private String status;

    public JobDto() {
    }

    public JobDto(Long jobId, Long companyId, String companyName, String title, String description,
                  String skillsRequired, Integer experienceRequired, String educationRequired, String location,
                  String salaryRange, String jobType, LocalDate deadline, Integer openings, String status) {
        this.jobId = jobId;
        this.companyId = companyId;
        this.companyName = companyName;
        this.title = title;
        this.description = description;
        this.skillsRequired = skillsRequired;
        this.experienceRequired = experienceRequired;
        this.educationRequired = educationRequired;
        this.location = location;
        this.salaryRange = salaryRange;
        this.jobType = jobType;
        this.deadline = deadline;
        this.openings = openings;
        this.status = status;
    }

    public static JobDtoBuilder builder() {
        return new JobDtoBuilder();
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkillsRequired() {
        return skillsRequired;
    }

    public void setSkillsRequired(String skillsRequired) {
        this.skillsRequired = skillsRequired;
    }

    public Integer getExperienceRequired() {
        return experienceRequired;
    }

    public void setExperienceRequired(Integer experienceRequired) {
        this.experienceRequired = experienceRequired;
    }

    public String getEducationRequired() {
        return educationRequired;
    }

    public void setEducationRequired(String educationRequired) {
        this.educationRequired = educationRequired;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Integer getOpenings() {
        return openings;
    }

    public void setOpenings(Integer openings) {
        this.openings = openings;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class JobDtoBuilder {
        private Long jobId;
        private Long companyId;
        private String companyName;
        private String title;
        private String description;
        private String skillsRequired;
        private Integer experienceRequired;
        private String educationRequired;
        private String location;
        private String salaryRange;
        private String jobType;
        private LocalDate deadline;
        private Integer openings;
        private String status;

        public JobDtoBuilder jobId(Long jobId) {
            this.jobId = jobId;
            return this;
        }

        public JobDtoBuilder companyId(Long companyId) {
            this.companyId = companyId;
            return this;
        }

        public JobDtoBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public JobDtoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public JobDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public JobDtoBuilder skillsRequired(String skillsRequired) {
            this.skillsRequired = skillsRequired;
            return this;
        }

        public JobDtoBuilder experienceRequired(Integer experienceRequired) {
            this.experienceRequired = experienceRequired;
            return this;
        }

        public JobDtoBuilder educationRequired(String educationRequired) {
            this.educationRequired = educationRequired;
            return this;
        }

        public JobDtoBuilder location(String location) {
            this.location = location;
            return this;
        }

        public JobDtoBuilder salaryRange(String salaryRange) {
            this.salaryRange = salaryRange;
            return this;
        }

        public JobDtoBuilder jobType(String jobType) {
            this.jobType = jobType;
            return this;
        }

        public JobDtoBuilder deadline(LocalDate deadline) {
            this.deadline = deadline;
            return this;
        }

        public JobDtoBuilder openings(Integer openings) {
            this.openings = openings;
            return this;
        }

        public JobDtoBuilder status(String status) {
            this.status = status;
            return this;
        }

        public JobDto build() {
            return new JobDto(jobId, companyId, companyName, title, description, skillsRequired, experienceRequired,
                    educationRequired, location, salaryRange, jobType, deadline, openings, status);
        }
    }
}
