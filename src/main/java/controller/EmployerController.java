package com.rev.app.revhire.controller;

import com.rev.app.revhire.entity.*;
import com.rev.app.revhire.repository.EmployerRepository;
import com.rev.app.revhire.repository.UserRepository;
import com.rev.app.revhire.service.ApplicationService;
import com.rev.app.revhire.service.CompanyService;
import com.rev.app.revhire.service.EmployerService;
import com.rev.app.revhire.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    private JobService jobService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployerService employerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployerRepository employerRepository;

    private Employer getCurrentEmployer(UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        return employerRepository.findByUser(user).orElseThrow();
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Employer employer = getCurrentEmployer(userDetails);
        if (employer.getCompany() != null) {
            model.addAttribute("jobs", jobService.getJobsByCompany(employer.getCompany().getCompanyId()));
            model.addAttribute("stats", employerService.getDashboardStats(employer));
        }
        model.addAttribute("employer", employer);
        return "employer/dashboard";
    }

    @GetMapping("/jobs/new")
    public String showJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "employer/job-form";
    }

    @PostMapping("/jobs/new")
    public String createJob(@AuthenticationPrincipal UserDetails userDetails,
                            @jakarta.validation.Valid @ModelAttribute Job job,
                            org.springframework.validation.BindingResult result) {
        if (result.hasErrors()) {
            return "employer/job-form";
        }
        Employer employer = getCurrentEmployer(userDetails);
        job.setCompany(employer.getCompany());
        jobService.createJob(job);
        return "redirect:/employer/dashboard?created";
    }

    @GetMapping("/applications/{jobId}")
    public String viewApplicants(@PathVariable Long jobId,
                                 @RequestParam(required = false) String status,
                                 @RequestParam(required = false) Integer minExperience,
                                 @RequestParam(required = false) String skills,
                                 Model model) {
        Job job = jobService.getJobById(jobId);
        model.addAttribute("job", job);
        model.addAttribute("applications", applicationService.findApplications(job, status, minExperience, skills));
        return "employer/applicants";
    }

    @GetMapping("/company")
    public String companyProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Employer employer = getCurrentEmployer(userDetails);
        model.addAttribute("company", employer.getCompany());
        return "employer/company-profile";
    }

    @PostMapping("/company")
    public String updateCompany(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute Company company) {
        companyService.saveCompany(company);
        return "redirect:/employer/company?success";
    }

    @PostMapping("/applications/{id}/status")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam String status,
                               @RequestParam(required = false) String internalNotes) {
        Application app = applicationService.getApplicationById(id);
        applicationService.updateApplicationStatus(id, status, internalNotes);
        return "redirect:/employer/applications/" + app.getJob().getJobId() + "?success";
    }

    @PostMapping("/jobs/{id}/close")
    public String closeJob(@PathVariable Long id) {
        jobService.closeJob(id);
        return "redirect:/employer/dashboard?closed";
    }

    @PostMapping("/jobs/{id}/reopen")
    public String reopenJob(@PathVariable Long id) {
        jobService.reopenJob(id);
        return "redirect:/employer/dashboard?reopened";
    }

    @PostMapping("/applications/bulk-status")
    public String bulkUpdateStatus(@RequestParam java.util.List<Long> applicationIds,
                                   @RequestParam String status,
                                   @RequestParam(required = false) String internalNotes) {
        applicationService.bulkUpdateStatus(applicationIds, status, internalNotes);
        return "redirect:/employer/dashboard?bulkSuccess";
    }
}
