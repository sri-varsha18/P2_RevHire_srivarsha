package com.rev.app.revhire.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_seq")
    @SequenceGenerator(name = "job_seq", sequenceName = "JOB_SEQ", allocationSize = 1)
    private Long jobId;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private com.rev.app.revhire.entity.Company company;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @Lob
    private String skillsRequired;

    private Integer experienceRequired;
    private String educationRequired;
    private String location;
    private String salaryRange;
    private String jobType;
    private LocalDate deadline;
    private Integer openings = 1;
    private String status = "OPEN";
    private LocalDateTime createdAt = LocalDateTime.now();

    public Job() {
    }

    public Job(Long jobId, com.rev.app.revhire.entity.Company company, String title, String description, String skillsRequired,
               Integer experienceRequired, String educationRequired, String location, String salaryRange, String jobType,
               LocalDate deadline, Integer openings, String status, LocalDateTime createdAt) {
        this.jobId = jobId;
        this.company = company;
        this.title = title;
        this.description = description;
        this.skillsRequired = skillsRequired;
        this.experienceRequired = experienceRequired;
        this.educationRequired = educationRequired;
        this.location = location;
        this.salaryRange = salaryRange;
        this.jobType = jobType;
        this.deadline = deadline;
        this.openings = openings != null ? openings : 1;
        this.status = status != null ? status : "OPEN";
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static JobBuilder builder() {
        return new JobBuilder();
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public com.rev.app.revhire.entity.Company getCompany() {
        return company;
    }

    public void setCompany(com.rev.app.revhire.entity.Company company) {
        this.company = company;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static class JobBuilder {
        private Long jobId;
        private com.rev.app.revhire.entity.Company company;
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
        private LocalDateTime createdAt;

        public JobBuilder jobId(Long jobId) {
            this.jobId = jobId;
            return this;
        }

        public JobBuilder company(com.rev.app.revhire.entity.Company company) {
            this.company = company;
            return this;
        }

        public JobBuilder title(String title) {
            this.title = title;
            return this;
        }

        public JobBuilder description(String description) {
            this.description = description;
            return this;
        }

        public JobBuilder skillsRequired(String skillsRequired) {
            this.skillsRequired = skillsRequired;
            return this;
        }

        public JobBuilder experienceRequired(Integer experienceRequired) {
            this.experienceRequired = experienceRequired;
            return this;
        }

        public JobBuilder educationRequired(String educationRequired) {
            this.educationRequired = educationRequired;
            return this;
        }

        public JobBuilder location(String location) {
            this.location = location;
            return this;
        }

        public JobBuilder salaryRange(String salaryRange) {
            this.salaryRange = salaryRange;
            return this;
        }

        public JobBuilder jobType(String jobType) {
            this.jobType = jobType;
            return this;
        }

        public JobBuilder deadline(LocalDate deadline) {
            this.deadline = deadline;
            return this;
        }

        public JobBuilder openings(Integer openings) {
            this.openings = openings;
            return this;
        }

        public JobBuilder status(String status) {
            this.status = status;
            return this;
        }

        public JobBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Job build() {
            return new Job(jobId, company, title, description, skillsRequired, experienceRequired, educationRequired,
                    location, salaryRange, jobType, deadline, openings, status, createdAt);
        }
    }
}
