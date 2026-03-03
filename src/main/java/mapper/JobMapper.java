package com.rev.app.revhire.mapper;

import com.rev.app.revhire.dto.JobDto;
import com.rev.app.revhire.entity.Job;
import com.rev.app.revhire.entity.Company;

public class JobMapper {
    public static JobDto toDto(Job job) {
        if (job == null)
            return null;
        return JobDto.builder()
                .jobId(job.getJobId())
                .companyId(job.getCompany() != null ? job.getCompany().getCompanyId() : null)
                .companyName(job.getCompany() != null ? job.getCompany().getName() : null)
                .title(job.getTitle())
                .description(job.getDescription())
                .skillsRequired(job.getSkillsRequired())
                .experienceRequired(job.getExperienceRequired())
                .educationRequired(job.getEducationRequired())
                .location(job.getLocation())
                .salaryRange(job.getSalaryRange())
                .jobType(job.getJobType())
                .deadline(job.getDeadline())
                .openings(job.getOpenings())
                .status(job.getStatus())
                .build();
    }

    public static Job toEntity(JobDto dto, Company company) {
        if (dto == null)
            return null;
        return Job.builder()
                .jobId(dto.getJobId())
                .company(company)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .skillsRequired(dto.getSkillsRequired())
                .experienceRequired(dto.getExperienceRequired())
                .educationRequired(dto.getEducationRequired())
                .location(dto.getLocation())
                .salaryRange(dto.getSalaryRange())
                .jobType(dto.getJobType())
                .deadline(dto.getDeadline())
                .openings(dto.getOpenings())
                .status(dto.getStatus())
                .build();
    }
}
