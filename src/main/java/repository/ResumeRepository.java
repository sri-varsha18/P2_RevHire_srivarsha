package com.rev.app.revhire.repository;

import com.rev.app.revhire.entity.JobSeeker;
import com.rev.app.revhire.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByJobSeeker(JobSeeker jobSeeker);
}
