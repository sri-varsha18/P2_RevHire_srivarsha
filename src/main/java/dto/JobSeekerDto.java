package com.rev.app.revhire.dto;

public class JobSeekerDto {
    private Long jobSeekerId;
    private Long userId;
    private String email;
    private String name;
    private String phone;
    private String location;
    private String employmentStatus;
    private Integer experienceYears;
    private Integer profileCompletion;

    public JobSeekerDto() {
    }

    public JobSeekerDto(Long jobSeekerId, Long userId, String email, String name, String phone, String location,
                        String employmentStatus, Integer experienceYears, Integer profileCompletion) {
        this.jobSeekerId = jobSeekerId;
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.employmentStatus = employmentStatus;
        this.experienceYears = experienceYears;
        this.profileCompletion = profileCompletion;
    }

    public static JobSeekerDtoBuilder builder() {
        return new JobSeekerDtoBuilder();
    }

    public Long getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(Long jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public static class JobSeekerDtoBuilder {
        private Long jobSeekerId;
        private Long userId;
        private String email;
        private String name;
        private String phone;
        private String location;
        private String employmentStatus;
        private Integer experienceYears;
        private Integer profileCompletion;

        public JobSeekerDtoBuilder jobSeekerId(Long jobSeekerId) {
            this.jobSeekerId = jobSeekerId;
            return this;
        }

        public JobSeekerDtoBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public JobSeekerDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public JobSeekerDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public JobSeekerDtoBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public JobSeekerDtoBuilder location(String location) {
            this.location = location;
            return this;
        }

        public JobSeekerDtoBuilder employmentStatus(String employmentStatus) {
            this.employmentStatus = employmentStatus;
            return this;
        }

        public JobSeekerDtoBuilder experienceYears(Integer experienceYears) {
            this.experienceYears = experienceYears;
            return this;
        }

        public JobSeekerDtoBuilder profileCompletion(Integer profileCompletion) {
            this.profileCompletion = profileCompletion;
            return this;
        }

        public JobSeekerDto build() {
            return new JobSeekerDto(jobSeekerId, userId, email, name, phone, location, employmentStatus,
                    experienceYears, profileCompletion);
        }
    }
}
