package com.rev.app.revhire.service;

import com.rev.app.revhire.entity.Employer;
import com.rev.app.revhire.repository.ApplicationRepository;
import com.rev.app.revhire.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticsService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public Map<String, Object> getEmployerStats(Employer employer) {
        Long companyId = employer.getCompany() != null ? employer.getCompany().getCompanyId() : null;
        Map<String, Object> stats = new HashMap<>();

        if (companyId != null) {
            stats.put("totalJobs", jobRepository.countByCompany_CompanyId(companyId));
            stats.put("activeJobs", jobRepository.countByCompany_CompanyIdAndStatus(companyId, "OPEN"));
            stats.put("totalApplications", applicationRepository.countByJob_Company_CompanyId(companyId));
            stats.put("pendingReviews",
                    applicationRepository.countByJob_Company_CompanyIdAndStatus(companyId, "APPLIED"));
        } else {
            stats.put("totalJobs", 0);
            stats.put("activeJobs", 0);
            stats.put("totalApplications", 0);
            stats.put("pendingReviews", 0);
        }

        return stats;
    }
}
