package com.rev.app.revhire.service;

import com.rev.app.revhire.entity.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class EmployerService {

    @Autowired
    private StatisticsService statisticsService;

    public Map<String, Object> getDashboardStats(Employer employer) {
        return statisticsService.getEmployerStats(employer);
    }
}
