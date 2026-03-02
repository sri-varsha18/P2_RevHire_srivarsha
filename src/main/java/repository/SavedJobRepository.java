package com.rev.app.revhire.repository;

import com.rev.app.revhire.entity.Job;
import com.rev.app.revhire.entity.JobSeeker;
import com.rev.app.revhire.entity.SavedJob;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {
    List<SavedJob> findByJobSeeker(JobSeeker jobSeeker);

    Optional<SavedJob> findByJobSeekerAndJob(JobSeeker jobSeeker, Job job);

    void deleteByJobSeekerAndJob(JobSeeker jobSeeker, Job job);

    boolean existsByJobSeekerAndJob(JobSeeker jobSeeker, Job job);
}
