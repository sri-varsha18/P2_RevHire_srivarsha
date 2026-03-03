package com.rev.app.revhire.dto;

public class EmployerDto {
    private Long employerId;
    private Long userId;
    private String email;
    private Long companyId;
    private String companyName;

    public EmployerDto() {
    }

    public EmployerDto(Long employerId, Long userId, String email, Long companyId, String companyName) {
        this.employerId = employerId;
        this.userId = userId;
        this.email = email;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public static EmployerDtoBuilder builder() {
        return new EmployerDtoBuilder();
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
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

    public static class EmployerDtoBuilder {
        private Long employerId;
        private Long userId;
        private String email;
        private Long companyId;
        private String companyName;

        public EmployerDtoBuilder employerId(Long employerId) {
            this.employerId = employerId;
            return this;
        }

        public EmployerDtoBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public EmployerDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public EmployerDtoBuilder companyId(Long companyId) {
            this.companyId = companyId;
            return this;
        }

        public EmployerDtoBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public EmployerDto build() {
            return new EmployerDto(employerId, userId, email, companyId, companyName);
        }
    }
}
