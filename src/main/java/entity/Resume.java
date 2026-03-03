package com.rev.app.revhire.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resume_seq")
    @SequenceGenerator(name = "resume_seq", sequenceName = "RESUME_SEQ", allocationSize = 1)
    private Long resumeId;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private com.rev.app.revhire.entity.JobSeeker jobSeeker;

    @Lob
    private String objective;
    @Lob
    private String education;
    @Lob
    private String experience;
    @Lob
    private String skills;
    @Lob
    private String projects;
    @Lob
    private String certifications;

    @Lob
    private byte[] resumeFileData;
    private String resumeFileName;
    private String resumeFileType;
    private Long resumeFileSize;

    public Resume() {
    }

    public Resume(Long resumeId, com.rev.app.revhire.entity.JobSeeker jobSeeker, String objective, String education, String experience,
                  String skills, String projects, String certifications, byte[] resumeFileData, String resumeFileName,
                  String resumeFileType, Long resumeFileSize) {
        this.resumeId = resumeId;
        this.jobSeeker = jobSeeker;
        this.objective = objective;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
        this.projects = projects;
        this.certifications = certifications;
        this.resumeFileData = resumeFileData;
        this.resumeFileName = resumeFileName;
        this.resumeFileType = resumeFileType;
        this.resumeFileSize = resumeFileSize;
    }

    public static ResumeBuilder builder() {
        return new ResumeBuilder();
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public com.rev.app.revhire.entity.JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(com.rev.app.revhire.entity.JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public byte[] getResumeFileData() {
        return resumeFileData;
    }

    public void setResumeFileData(byte[] resumeFileData) {
        this.resumeFileData = resumeFileData;
    }

    public String getResumeFileName() {
        return resumeFileName;
    }

    public void setResumeFileName(String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }

    public String getResumeFileType() {
        return resumeFileType;
    }

    public void setResumeFileType(String resumeFileType) {
        this.resumeFileType = resumeFileType;
    }

    public Long getResumeFileSize() {
        return resumeFileSize;
    }

    public void setResumeFileSize(Long resumeFileSize) {
        this.resumeFileSize = resumeFileSize;
    }

    public static class ResumeBuilder {
        private Long resumeId;
        private com.rev.app.revhire.entity.JobSeeker jobSeeker;
        private String objective;
        private String education;
        private String experience;
        private String skills;
        private String projects;
        private String certifications;
        private byte[] resumeFileData;
        private String resumeFileName;
        private String resumeFileType;
        private Long resumeFileSize;

        public ResumeBuilder resumeId(Long resumeId) {
            this.resumeId = resumeId;
            return this;
        }

        public ResumeBuilder jobSeeker(com.rev.app.revhire.entity.JobSeeker jobSeeker) {
            this.jobSeeker = jobSeeker;
            return this;
        }

        public ResumeBuilder objective(String objective) {
            this.objective = objective;
            return this;
        }

        public ResumeBuilder education(String education) {
            this.education = education;
            return this;
        }

        public ResumeBuilder experience(String experience) {
            this.experience = experience;
            return this;
        }

        public ResumeBuilder skills(String skills) {
            this.skills = skills;
            return this;
        }

        public ResumeBuilder projects(String projects) {
            this.projects = projects;
            return this;
        }

        public ResumeBuilder certifications(String certifications) {
            this.certifications = certifications;
            return this;
        }

        public ResumeBuilder resumeFileData(byte[] resumeFileData) {
            this.resumeFileData = resumeFileData;
            return this;
        }

        public ResumeBuilder resumeFileName(String resumeFileName) {
            this.resumeFileName = resumeFileName;
            return this;
        }

        public ResumeBuilder resumeFileType(String resumeFileType) {
            this.resumeFileType = resumeFileType;
            return this;
        }

        public ResumeBuilder resumeFileSize(Long resumeFileSize) {
            this.resumeFileSize = resumeFileSize;
            return this;
        }

        public Resume build() {
            return new Resume(resumeId, jobSeeker, objective, education, experience, skills, projects, certifications,
                    resumeFileData, resumeFileName, resumeFileType, resumeFileSize);
        }
    }
}
