package com.rev.app.revhire.mapper;

import com.rev.app.revhire.dto.JobSeekerDto;
import com.rev.app.revhire.entity.JobSeeker;
import com.rev.app.revhire.entity.User;

public class JobSeekerMapper {
    public static JobSeekerDto toDto(JobSeeker jobSeeker) {
        if (jobSeeker == null)
            return null;
        return JobSeekerDto.builder()
                .jobSeekerId(jobSeeker.getJobSeekerId())
                .userId(jobSeeker.getUser() != null ? jobSeeker.getUser().getUserId() : null)
                .email(jobSeeker.getUser() != null ? jobSeeker.getUser().getEmail() : null)
                .name(jobSeeker.getName())
                .phone(jobSeeker.getPhone())
                .location(jobSeeker.getLocation())
                .employmentStatus(jobSeeker.getEmploymentStatus())
                .experienceYears(jobSeeker.getExperienceYears())
                .profileCompletion(jobSeeker.getProfileCompletion())
                .build();
    }

    public static JobSeeker toEntity(JobSeekerDto dto, User user) {
        if (dto == null)
            return null;
        return JobSeeker.builder()
                .jobSeekerId(dto.getJobSeekerId())
                .user(user)
                .name(dto.getName())
                .phone(dto.getPhone())
                .location(dto.getLocation())
                .employmentStatus(dto.getEmploymentStatus())
                .experienceYears(dto.getExperienceYears())
                .profileCompletion(dto.getProfileCompletion())
                .build();
    }
}
