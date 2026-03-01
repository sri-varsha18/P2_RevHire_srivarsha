package com.rev.app.revhire.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "job_seekers")
public class JobSeeker {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_seeker_seq")
    @SequenceGenerator(name = "job_seeker_seq", sequenceName = "JOB_SEEKER_SEQ", allocationSize = 1)
    private Long jobSeekerId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    private String phone;
    private String location;
    private String employmentStatus;
    private Integer experienceYears;
    private Integer profileCompletion = 0;

    @Lob
    private String skills;
    @Lob
    private String education;
    @Lob
    private String experience;
    @Lob
    private String certifications;
    @Lob
    private String projects;

    public JobSeeker() {
    }

    public JobSeeker(Long jobSeekerId, User user, String name, String phone, String location, String employmentStatus,
                     Integer experienceYears, Integer profileCompletion, String skills, String education, String experience,
                     String certifications, String projects) {
        this.jobSeekerId = jobSeekerId;
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.employmentStatus = employmentStatus;
        this.experienceYears = experienceYears;
        this.profileCompletion = profileCompletion != null ? profileCompletion : 0;
        this.skills = skills;
        this.education = education;
        this.experience = experience;
        this.certifications = certifications;
        this.projects = projects;
    }

    public static JobSeekerBuilder builder() {
        return new JobSeekerBuilder();
    }

    public Long getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(Long jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public Integer getProfileCompletion() {
        return profileCompletion;
    }

    public void setProfileCompletion(Integer profileCompletion) {
        this.profileCompletion = profileCompletion;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public static class JobSeekerBuilder {
        private Long jobSeekerId;
        private User user;
        private String name;
        private String phone;
        private String location;
        private String employmentStatus;
        private Integer experienceYears;
        private Integer profileCompletion;
        private String skills;
        private String education;
        private String experience;
        private String certifications;
        private String projects;

        public JobSeekerBuilder jobSeekerId(Long jobSeekerId) {
            this.jobSeekerId = jobSeekerId;
            return this;
        }

        public JobSeekerBuilder user(User user) {
            this.user = user;
            return this;
        }

        public JobSeekerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public JobSeekerBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public JobSeekerBuilder location(String location) {
            this.location = location;
            return this;
        }

        public JobSeekerBuilder employmentStatus(String employmentStatus) {
            this.employmentStatus = employmentStatus;
            return this;
        }

        public JobSeekerBuilder experienceYears(Integer experienceYears) {
            this.experienceYears = experienceYears;
            return this;
        }

        public JobSeekerBuilder profileCompletion(Integer profileCompletion) {
            this.profileCompletion = profileCompletion;
            return this;
        }

        public JobSeekerBuilder skills(String skills) {
            this.skills = skills;
            return this;
        }

        public JobSeekerBuilder education(String education) {
            this.education = education;
            return this;
        }

        public JobSeekerBuilder experience(String experience) {
            this.experience = experience;
            return this;
        }

        public JobSeekerBuilder certifications(String certifications) {
            this.certifications = certifications;
            return this;
        }

        public JobSeekerBuilder projects(String projects) {
            this.projects = projects;
            return this;
        }

        public JobSeeker build() {
            return new JobSeeker(jobSeekerId, user, name, phone, location, employmentStatus, experienceYears,
                    profileCompletion, skills, education, experience, certifications, projects);
        }
    }
}
