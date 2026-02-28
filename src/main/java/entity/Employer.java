package com.rev.app.revhire.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employers")
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employerId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Employer() {
    }

    public Employer(Long employerId, User user, Company company) {
        this.employerId = employerId;
        this.user = user;
        this.company = company;
    }

    public static EmployerBuilder builder() {
        return new EmployerBuilder();
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static class EmployerBuilder {
        private Long employerId;
        private User user;
        private Company company;

        public EmployerBuilder employerId(Long employerId) {
            this.employerId = employerId;
            return this;
        }

        public EmployerBuilder user(User user) {
            this.user = user;
            return this;
        }

        public EmployerBuilder company(Company company) {
            this.company = company;
            return this;
        }

        public Employer build() {
            return new Employer(employerId, user, company);
        }
    }
}
