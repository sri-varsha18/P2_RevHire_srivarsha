package com.rev.app.revhire.mapper;

import com.rev.app.revhire.dto.ApplicationDto;
import com.rev.app.revhire.entity.Application;
import com.rev.app.revhire.entity.Job;
import com.rev.app.revhire.entity.JobSeeker;
import com.rev.app.revhire.entity.Resume;

public class ApplicationMapper {
    public static ApplicationDto toDto(Application app) {
        if (app == null)
            return null;
        return ApplicationDto.builder()
                .applicationId(app.getApplicationId())
                .jobId(app.getJob() != null ? app.getJob().getJobId() : null)
                .jobTitle(app.getJob() != null ? app.getJob().getTitle() : null)
                .jobSeekerId(app.getJobSeeker() != null ? app.getJobSeeker().getJobSeekerId() : null)
                .jobSeekerName(app.getJobSeeker() != null ? app.getJobSeeker().getName() : null)
                .resumeId(app.getResume() != null ? app.getResume().getResumeId() : null)
                .coverLetter(app.getCoverLetter())
                .status(app.getStatus())
                .appliedDate(app.getAppliedDate())
                .withdrawReason(app.getWithdrawReason())
                .internalNotes(app.getInternalNotes())
                .build();
    }

    public static Application toEntity(ApplicationDto dto, Job job, JobSeeker jobSeeker, Resume resume) {
        if (dto == null)
            return null;
        return Application.builder()
                .applicationId(dto.getApplicationId())
                .job(job)
                .jobSeeker(jobSeeker)
                .resume(resume)
                .coverLetter(dto.getCoverLetter())
                .status(dto.getStatus())
                .appliedDate(dto.getAppliedDate())
                .withdrawReason(dto.getWithdrawReason())
                .internalNotes(dto.getInternalNotes())
                .build();
    }
}
