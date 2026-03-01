package com.rev.app.revhire.mapper;

import com.rev.app.revhire.dto.EmployerDto;
import com.rev.app.revhire.entity.Employer;
import com.rev.app.revhire.entity.Company;
import com.rev.app.revhire.entity.User;

public class EmployerMapper {
    public static EmployerDto toDto(Employer employer) {
        if (employer == null)
            return null;
        return EmployerDto.builder()
                .employerId(employer.getEmployerId())
                .userId(employer.getUser() != null ? employer.getUser().getUserId() : null)
                .email(employer.getUser() != null ? employer.getUser().getEmail() : null)
                .companyId(employer.getCompany() != null ? employer.getCompany().getCompanyId() : null)
                .companyName(employer.getCompany() != null ? employer.getCompany().getName() : null)
                .build();
    }

    public static Employer toEntity(EmployerDto dto, User user, Company company) {
        if (dto == null)
            return null;
        return Employer.builder()
                .employerId(dto.getEmployerId())
                .user(user)
                .company(company)
                .build();
    }
}
