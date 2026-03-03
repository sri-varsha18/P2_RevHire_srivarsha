package com.rev.app.revhire.controller;

import com.rev.app.revhire.entity.Application;
import com.rev.app.revhire.entity.Job;
import com.rev.app.revhire.entity.JobSeeker;
import com.rev.app.revhire.entity.Resume;
import com.rev.app.revhire.entity.User;
import com.rev.app.revhire.repository.JobSeekerRepository;
import com.rev.app.revhire.repository.UserRepository;
import com.rev.app.revhire.service.ApplicationService;
import com.rev.app.revhire.service.JobService;
import com.rev.app.revhire.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @GetMapping
    public String listJobs(@RequestParam(required = false) String keyword,
                           @RequestParam(required = false) String location,
                           @RequestParam(required = false) String type,
                           @RequestParam(required = false) Integer minExperience,
                           @RequestParam(required = false) String company,
                           @RequestParam(required = false) String minSalary,
                           @RequestParam(required = false) Integer postedAfter,
                           Model model) {

        java.time.LocalDate postedAfterDate = null;
        if (postedAfter != null) {
            postedAfterDate = java.time.LocalDate.now().minusDays(postedAfter);
        }

        model.addAttribute("jobs",
                jobService.findJobs(keyword, location, minExperience, company, type, minSalary, postedAfterDate));
        return "jobs/list";
    }

    @GetMapping("/{id}")
    public String viewJob(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobService.getJobById(id));
        return "jobs/detail";
    }

    @PostMapping("/{id}/apply")
    public String apply(@PathVariable Long id,
                        @AuthenticationPrincipal UserDetails userDetails,
                        @RequestParam(required = false) String coverLetter) {
        if (userDetails == null)
            return "redirect:/login";

        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        JobSeeker seeker = jobSeekerRepository.findByUser(user).orElseThrow();
        Job job = jobService.getJobById(id);
        Resume resume = resumeService.getResumeByJobSeeker(seeker);

        Application application = Application.builder()
                .job(job)
                .jobSeeker(seeker)
                .resume(resume)
                .coverLetter(coverLetter)
                .build();

        applicationService.applyForJob(application);
        return "redirect:/jobseeker/dashboard?applied";
    }
}
