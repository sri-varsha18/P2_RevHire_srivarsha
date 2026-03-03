package com.rev.app.revhire.controller;

import com.rev.app.revhire.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private JobService jobService;

    @GetMapping({ "/", "/home" })
    public String index(Model model) {
        model.addAttribute("jobs", jobService.getAllOpenJobs());
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String title,
                         @RequestParam(required = false) String location,
                         @RequestParam(required = false) Integer experience,
                         @RequestParam(required = false) String companyName,
                         @RequestParam(required = false) String jobType,
                         @RequestParam(required = false) String salaryRange,
                         Model model) {
        model.addAttribute("jobs",
                jobService.findJobs(title, location, experience, companyName, jobType, salaryRange, null));
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYER"))) {
            return "redirect:/employer/dashboard";
        } else if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_JOBSEEKER"))) {
            return "redirect:/jobseeker/dashboard";
        }
        return "redirect:/";
    }
}
