package com.rev.app.revhire.service;

import com.rev.app.revhire.entity.JobSeeker;
import com.rev.app.revhire.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public JobSeeker getJobSeekerById(Long id) {
        return jobSeekerRepository.findById(id).orElseThrow(() -> new RuntimeException("Job Seeker not found"));
    }

    public List<JobSeeker> getAllJobSeekers() {
        return jobSeekerRepository.findAll();
    }
}
