package com.rev.app.revhire.service;

import com.rev.app.revhire.entity.Job;
import com.rev.app.revhire.entity.JobSeeker;
import com.rev.app.revhire.entity.SavedJob;
import com.rev.app.revhire.repository.SavedJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SavedJobService {

    @Autowired
    private SavedJobRepository savedJobRepository;

    @Transactional
    public void saveJob(JobSeeker seeker, Job job) {
        if (!savedJobRepository.existsByJobSeekerAndJob(seeker, job)) {
            SavedJob savedJob = SavedJob.builder()
                    .jobSeeker(seeker)
                    .job(job)
                    .build();
            savedJobRepository.save(savedJob);
            savedJobRepository.flush();
        }
    }

    @Transactional
    public void unsaveJob(JobSeeker seeker, Job job) {
        savedJobRepository.findByJobSeekerAndJob(seeker, job)
                .ifPresent(savedJob -> {
                    savedJobRepository.delete(savedJob);
                    savedJobRepository.flush();
                });
    }

    public List<SavedJob> getSavedJobs(JobSeeker seeker) {
        return savedJobRepository.findByJobSeeker(seeker);
    }

    public boolean isJobSaved(JobSeeker seeker, Job job) {
        return savedJobRepository.existsByJobSeekerAndJob(seeker, job);
    }
}
