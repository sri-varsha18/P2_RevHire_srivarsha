package com.rev.app.revhire.controller;

import com.rev.app.revhire.entity.*;
import com.rev.app.revhire.repository.UserRepository;
import com.rev.app.revhire.repository.JobSeekerRepository;
import com.rev.app.revhire.service.ApplicationService;
import com.rev.app.revhire.service.ProfileService;
import com.rev.app.revhire.service.ResumeService;
import com.rev.app.revhire.service.SavedJobService;
import com.rev.app.revhire.service.JobService;
import com.rev.app.revhire.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
@RequestMapping("/jobseeker")
public class JobSeekerController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private SavedJobService savedJobService;

    @Autowired
    private JobService jobService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private NotificationService notificationService;

    private JobSeeker getCurrentSeeker(UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        return jobSeekerRepository.findByUser(user).orElseThrow();
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        JobSeeker seeker = getCurrentSeeker(userDetails);
        model.addAttribute("jobSeeker", seeker);

        java.util.List<Application> applications = applicationService.getApplicationsByJobSeeker(seeker);
        model.addAttribute("recentApplications", applications); // The template expects recentApplications

        java.util.Map<String, Object> stats = new java.util.HashMap<>();
        stats.put("totalApplications", applications.size());
        stats.put("shortlisted", applications.stream().filter(a -> "SHORTLISTED".equalsIgnoreCase(a.getStatus())).count());
        stats.put("savedJobs", savedJobService.getSavedJobs(seeker).size());

        model.addAttribute("stats", stats);

        return "jobseeker/dashboard";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("jobSeeker", getCurrentSeeker(userDetails));
        return "jobseeker/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal UserDetails userDetails,
                                @ModelAttribute JobSeeker seekerDetails) {
        JobSeeker seeker = getCurrentSeeker(userDetails);
        profileService.updateJobSeekerProfile(seeker.getJobSeekerId(), seekerDetails);
        return "redirect:/jobseeker/profile?success";
    }

    @GetMapping("/resume")
    public String resume(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        JobSeeker seeker = getCurrentSeeker(userDetails);
        Resume resume = resumeService.getResumeByJobSeeker(seeker);
        if (resume == null) {
            resume = Resume.builder().jobSeeker(seeker).build();
        }

        model.addAttribute("jobSeeker", seeker); // Provide jobSeeker for the sidebar UI
        model.addAttribute("resume", resume);
        return "jobseeker/resume";
    }

    @PostMapping("/resume/text")
    public String saveTextualResume(@AuthenticationPrincipal UserDetails userDetails,
                                    @ModelAttribute Resume resumeDetails) {
        JobSeeker seeker = getCurrentSeeker(userDetails);
        Resume existing = resumeService.getResumeByJobSeeker(seeker);

        if (existing != null) {
            existing.setObjective(resumeDetails.getObjective());
            existing.setEducation(resumeDetails.getEducation());
            existing.setExperience(resumeDetails.getExperience());
            existing.setSkills(resumeDetails.getSkills());
            existing.setProjects(resumeDetails.getProjects());
            existing.setCertifications(resumeDetails.getCertifications());
            resumeService.updateResume(existing.getResumeId(), existing);
        } else {
            resumeDetails.setJobSeeker(seeker);
            resumeService.saveResume(resumeDetails);
        }
        return "redirect:/jobseeker/resume?success";
    }

    @PostMapping("/resume/upload")
    public String uploadResumeFile(@AuthenticationPrincipal UserDetails userDetails,
                                   @RequestParam("file") MultipartFile file) throws IOException {
        JobSeeker seeker = getCurrentSeeker(userDetails);
        Resume resume = resumeService.getResumeByJobSeeker(seeker);

        if (resume == null) {
            resume = Resume.builder().jobSeeker(seeker).build();
        }

        if (file != null && !file.isEmpty()) {
            if (file.getSize() > 2 * 1024 * 1024) {
                return "redirect:/jobseeker/resume?error=file_too_large";
            }
            String contentType = file.getContentType();
            if (contentType != null && (contentType.equals("application/pdf")
                    || contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
                resume.setResumeFileData(file.getBytes());
                resume.setResumeFileName(file.getOriginalFilename());
                resume.setResumeFileType(file.getContentType());
                resume.setResumeFileSize(file.getSize());

                if (resume.getResumeId() != null) {
                    resumeService.updateResume(resume.getResumeId(), resume);
                } else {
                    resumeService.saveResume(resume);
                }
            } else {
                return "redirect:/jobseeker/resume?error=invalid_file_type";
            }
        }

        return "redirect:/jobseeker/resume?success";
    }

    @GetMapping("/saved-jobs")
    public String savedJobs(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        JobSeeker seeker = getCurrentSeeker(userDetails);
        model.addAttribute("savedJobs", savedJobService.getSavedJobs(seeker));
        model.addAttribute("jobSeeker", seeker);
        return "jobseeker/saved-jobs";
    }

    @GetMapping("/applications")
    public String applications(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        JobSeeker seeker = getCurrentSeeker(userDetails);
        java.util.List<Application> allApps = applicationService.getApplicationsByJobSeeker(seeker);
        model.addAttribute("applications", allApps);
        model.addAttribute("jobSeeker", seeker);

        // Status counts for overview cards
        model.addAttribute("countAll", allApps.size());
        model.addAttribute("countApplied",
                allApps.stream().filter(a -> "APPLIED".equalsIgnoreCase(a.getStatus())).count());
        model.addAttribute("countReview",
                allApps.stream().filter(a -> "UNDER REVIEW".equalsIgnoreCase(a.getStatus())).count());
        model.addAttribute("countShortlisted",
                allApps.stream().filter(a -> "SHORTLISTED".equalsIgnoreCase(a.getStatus())).count());
        model.addAttribute("countRejected",
                allApps.stream().filter(a -> "REJECTED".equalsIgnoreCase(a.getStatus())).count());
        model.addAttribute("countWithdrawn",
                allApps.stream().filter(a -> "WITHDRAWN".equalsIgnoreCase(a.getStatus())).count());

        return "jobseeker/applications";
    }

    @PostMapping("/jobs/{id}/save")
    public String saveJob(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        JobSeeker seeker = getCurrentSeeker(userDetails);
        Job job = jobService.getJobById(id);
        savedJobService.saveJob(seeker, job);
        return "redirect:/jobs/" + id + "?saved";
    }

    @PostMapping("/jobs/{id}/unsave")
    public String unsaveJob(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        JobSeeker seeker = getCurrentSeeker(userDetails);
        Job job = jobService.getJobById(id);
        savedJobService.unsaveJob(seeker, job);
        return "redirect:/jobseeker/saved-jobs?unsaved";
    }

    @PostMapping("/applications/{id}/withdraw")
    public String withdraw(@AuthenticationPrincipal UserDetails userDetails,
                           @PathVariable Long id,
                           @RequestParam(required = false) String reason) {
        JobSeeker seeker = getCurrentSeeker(userDetails);
        Application application = applicationService.getApplicationById(id);
        if (!application.getJobSeeker().getJobSeekerId().equals(seeker.getJobSeekerId())) {
            throw new RuntimeException("Unauthorized");
        }
        applicationService.withdrawApplication(id, reason);
        return "redirect:/jobseeker/applications?withdrawn";
    }

    @GetMapping("/notifications")
    public String notifications(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        JobSeeker seeker = jobSeekerRepository.findByUser(user).orElseThrow();

        model.addAttribute("notifications", notificationService.getAllNotifications(user));
        model.addAttribute("jobSeeker", seeker);
        return "jobseeker/notifications";
    }

    @PostMapping("/resume/delete-file")
    public String deleteResumeFile(@AuthenticationPrincipal UserDetails userDetails) {
        JobSeeker seeker = getCurrentSeeker(userDetails);
        Resume resume = resumeService.getResumeByJobSeeker(seeker);
        if (resume != null) {
            resume.setResumeFileData(null);
            resume.setResumeFileName(null);
            resume.setResumeFileType(null);
            resume.setResumeFileSize(null);
            resumeService.updateResume(resume.getResumeId(), resume);
        }
        return "redirect:/jobseeker/resume?deleted";
    }
}
