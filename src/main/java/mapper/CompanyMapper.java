package com.rev.app.revhire.mapper;

import com.rev.app.revhire.dto.CompanyDto;
import com.rev.app.revhire.entity.Company;

public class CompanyMapper {
    public static CompanyDto toDto(Company company) {
        if (company == null)
            return null;
        return CompanyDto.builder()
                .companyId(company.getCompanyId())
                .name(company.getName())
                .industry(company.getIndustry())
                .size(company.getSize())
                .description(company.getDescription())
                .website(company.getWebsite())
                .location(company.getLocation())
                .build();
    }

    public static Company toEntity(CompanyDto dto) {
        if (dto == null)
            return null;
        return Company.builder()
                .companyId(dto.getCompanyId())
                .name(dto.getName())
                .industry(dto.getIndustry())
                .size(dto.getSize())
                .description(dto.getDescription())
                .website(dto.getWebsite())
                .location(dto.getLocation())
                .build();
    }
}
