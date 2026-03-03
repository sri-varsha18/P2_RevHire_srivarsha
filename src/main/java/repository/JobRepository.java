package com.rev.app.revhire.repository;

import com.rev.app.revhire.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
    List<Job> findByStatus(String status);

    List<Job> findByCompany_CompanyId(Long companyId);

    long countByCompany_CompanyId(Long companyId);

    long countByCompany_CompanyIdAndStatus(Long companyId, String status);

    @Query("SELECT j FROM Job j WHERE " +
            "(:title IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
            "(:jobType IS NULL OR j.jobType = :jobType) AND " +
            "j.status = 'OPEN'")
    List<Job> searchJobs(@Param("title") String title,
                         @Param("location") String location,
                         @Param("jobType") String jobType);
}
