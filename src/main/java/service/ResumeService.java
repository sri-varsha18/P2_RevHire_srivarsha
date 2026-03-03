package com.rev.app.revhire.service;

import com.rev.app.revhire.entity.JobSeeker;
import com.rev.app.revhire.entity.Resume;
import com.rev.app.revhire.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    public Resume getResumeByJobSeeker(JobSeeker jobSeeker) {
        return resumeRepository.findByJobSeeker(jobSeeker).orElse(null);
    }

    @Transactional
    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    @Transactional
    public Resume updateResume(Long id, Resume resumeDetails) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
        resume.setObjective(resumeDetails.getObjective());
        resume.setEducation(resumeDetails.getEducation());
        resume.setExperience(resumeDetails.getExperience());
        resume.setSkills(resumeDetails.getSkills());
        resume.setProjects(resumeDetails.getProjects());
        resume.setCertifications(resumeDetails.getCertifications());

        // Update file data if provided in details
        if (resumeDetails.getResumeFileData() != null) {
            resume.setResumeFileData(resumeDetails.getResumeFileData());
            resume.setResumeFileName(resumeDetails.getResumeFileName());
            resume.setResumeFileType(resumeDetails.getResumeFileType());
            resume.setResumeFileSize(resumeDetails.getResumeFileSize());
        }

        return resumeRepository.save(resume);
    }

    @Transactional
    public Resume uploadResume(JobSeeker seeker, org.springframework.web.multipart.MultipartFile file)
            throws java.io.IOException {
        Resume resume = resumeRepository.findByJobSeeker(seeker).orElse(new Resume());
        resume.setJobSeeker(seeker);
        resume.setResumeFileData(file.getBytes());
        resume.setResumeFileName(file.getOriginalFilename());
        resume.setResumeFileType(file.getContentType());
        resume.setResumeFileSize(file.getSize());
        return resumeRepository.save(resume);
    }

    @Transactional
    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }
}
