package com.rev.app.revhire.repository;

import com.rev.app.revhire.entity.Application;
import com.rev.app.revhire.entity.Job;
import com.rev.app.revhire.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long>, JpaSpecificationExecutor<Application> {
    List<Application> findByJob(Job job);

    List<Application> findByJobSeeker(JobSeeker jobSeeker);

    boolean existsByJobAndJobSeeker(Job job, JobSeeker jobSeeker);

    long countByJob(Job job);

    long countByJob_Company_CompanyId(Long companyId);

    long countByJob_Company_CompanyIdAndStatus(Long companyId, String status);
}
