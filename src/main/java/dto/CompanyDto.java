package com.rev.app.revhire.dto;

public class CompanyDto {
    private Long companyId;
    private String name;
    private String industry;
    private String size;
    private String description;
    private String website;
    private String location;

    public CompanyDto() {
    }

    public CompanyDto(Long companyId, String name, String industry, String size, String description, String website,
                      String location) {
        this.companyId = companyId;
        this.name = name;
        this.industry = industry;
        this.size = size;
        this.description = description;
        this.website = website;
        this.location = location;
    }

    public static CompanyDtoBuilder builder() {
        return new CompanyDtoBuilder();
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

    public static class CompanyDtoBuilder {
        private Long companyId;
        private String name;
        private String industry;
        private String size;
        private String description;
        private String website;
        private String location;

        public CompanyDtoBuilder companyId(Long companyId) {
            this.companyId = companyId;
            return this;
        }

        public CompanyDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CompanyDtoBuilder industry(String industry) {
            this.industry = industry;
            return this;
        }

        public CompanyDtoBuilder size(String size) {
            this.size = size;
            return this;
        }

        public CompanyDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CompanyDtoBuilder website(String website) {
            this.website = website;
            return this;
        }

        public CompanyDtoBuilder location(String location) {
            this.location = location;
            return this;
        }

        public CompanyDto build() {
            return new CompanyDto(companyId, name, industry, size, description, website, location);
        }
    }
}
