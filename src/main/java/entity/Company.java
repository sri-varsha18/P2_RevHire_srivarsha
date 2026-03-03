package com.rev.app.revhire.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @SequenceGenerator(name = "company_seq", sequenceName = "COMPANY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    private Long companyId;

    @Column(nullable = false)
    private String name;

    private String industry;

    @Column(name = "company_size")
    private String size;

    @Lob
    private String description;

    private String website;
    private String location;

    public Company() {
    }

    public Company(Long companyId, String name, String industry, String size, String description, String website,
                   String location) {
        this.companyId = companyId;
        this.name = name;
        this.industry = industry;
        this.size = size;
        this.description = description;
        this.website = website;
        this.location = location;
    }

    public static CompanyBuilder builder() {
        return new CompanyBuilder();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static class CompanyBuilder {
        private Long companyId;
        private String name;
        private String industry;
        private String size;
        private String description;
        private String website;
        private String location;

        public CompanyBuilder companyId(Long companyId) {
            this.companyId = companyId;
            return this;
        }

        public CompanyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CompanyBuilder industry(String industry) {
            this.industry = industry;
            return this;
        }

        public CompanyBuilder size(String size) {
            this.size = size;
            return this;
        }

        public CompanyBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CompanyBuilder website(String website) {
            this.website = website;
            return this;
        }

        public CompanyBuilder location(String location) {
            this.location = location;
            return this;
        }

        public Company build() {
            return new Company(companyId, name, industry, size, description, website, location);
        }
    }
}
