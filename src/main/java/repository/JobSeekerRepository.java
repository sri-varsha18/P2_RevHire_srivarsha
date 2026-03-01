package com.rev.app.revhire.repository;

import com.rev.app.revhire.entity.JobSeeker;
import com.rev.app.revhire.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {
    Optional<JobSeeker> findByUser(User user);

    Optional<JobSeeker> findByUser_Email(String email);
}
