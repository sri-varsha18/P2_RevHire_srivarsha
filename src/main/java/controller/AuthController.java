package com.rev.app.revhire.controller;

import com.rev.app.revhire.dto.UserRegistrationDto;
import com.rev.app.revhire.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("mode", "login");
        UserRegistrationDto user = new UserRegistrationDto();
        user.setRole("JOBSEEKER");
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/login-select")
    public String loginSelect() {
        return "login-select";
    }

    @GetMapping("/register-select")
    public String registerSelect() {
        return "register-select";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("mode", "register");
        UserRegistrationDto user = new UserRegistrationDto();
        user.setRole("JOBSEEKER");
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping("/register/employer")
    public String showEmployerRegistrationForm(Model model) {
        model.addAttribute("mode", "register");
        UserRegistrationDto user = new UserRegistrationDto();
        user.setRole("EMPLOYER");
        model.addAttribute("user", user);
        return "employer-register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto registrationDto,
                               org.springframework.validation.BindingResult result,
                               Model model) {
        logger.info("Received registration request for email: {}", registrationDto.getEmail());

        if (registrationDto.getName() == null || registrationDto.getName().trim().isEmpty()) {
            result.rejectValue("name", "error.user", "Full Name is required");
        }
        if (registrationDto.getEmail() == null || registrationDto.getEmail().trim().isEmpty()) {
            result.rejectValue("email", "error.user", "Email Address is required");
        }
        if (registrationDto.getPassword() == null || registrationDto.getPassword().trim().isEmpty()) {
            result.rejectValue("password", "error.user", "Password is required");
        }

        if (registrationDto.getPassword() != null
                && !registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.user", "Passwords do not match");
        }

        if (result.hasErrors()) {
            logger.warn("Registration form has errors: {}", result.getAllErrors());
            model.addAttribute("mode", "register");
            model.addAttribute("validationErrors", result.getAllErrors());
            return "register";
        }
        try {
            authService.registerUser(registrationDto);
            logger.info("Successfully registered job seeker with email: {}", registrationDto.getEmail());
            // Show success message and redirect via JS
            model.addAttribute("successMessage", "Registration Successful! You can now log in.");
            model.addAttribute("mode", "register");
            return "register";
        } catch (Exception e) {
            logger.error("Registration failed for email {}: {}", registrationDto.getEmail(), e.getMessage());
            model.addAttribute("error", e.getMessage());
            model.addAttribute("mode", "register");
            return "register";
        }
    }

    @PostMapping("/register/employer")
    public String registerEmployer(@ModelAttribute("user") UserRegistrationDto registrationDto,
                                   org.springframework.validation.BindingResult result,
                                   Model model) {
        logger.info("Received employer registration request for email: {} and company: {}", registrationDto.getEmail(),
                registrationDto.getCompanyName());

        // Custom Validation for Employer
        if (registrationDto.getCompanyName() == null || registrationDto.getCompanyName().trim().isEmpty()) {
            result.rejectValue("companyName", "error.user", "Company Name is required");
        }
        if (registrationDto.getIndustry() == null || registrationDto.getIndustry().trim().isEmpty()) {
            result.rejectValue("industry", "error.user", "Industry is required");
        }
        if (registrationDto.getCompanySize() == null || registrationDto.getCompanySize().trim().isEmpty()) {
            result.rejectValue("companySize", "error.user", "Company Size is required");
        }
        if (registrationDto.getCompanyDescription() == null
                || registrationDto.getCompanyDescription().trim().isEmpty()) {
            result.rejectValue("companyDescription", "error.user", "Company Description is required");
        }
        if (registrationDto.getLocation() == null || registrationDto.getLocation().trim().isEmpty()) {
            result.rejectValue("location", "error.user", "Location is required");
        }
        if (registrationDto.getName() == null || registrationDto.getName().trim().isEmpty()) {
            result.rejectValue("name", "error.user", "Representative Name is required");
        }
        if (registrationDto.getEmail() == null || registrationDto.getEmail().trim().isEmpty()) {
            result.rejectValue("email", "error.user", "Official Email is required");
        }
        if (registrationDto.getPassword() == null || registrationDto.getPassword().trim().isEmpty()) {
            result.rejectValue("password", "error.user", "Password is required");
        }

        if (registrationDto.getPassword() != null
                && !registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.user", "Passwords do not match");
        }

        if (result.hasErrors()) {
            logger.warn("Employer registration form has errors: {}", result.getAllErrors());
            model.addAttribute("mode", "register");
            model.addAttribute("validationErrors", result.getAllErrors());
            return "employer-register";
        }
        try {
            // Force role to EMPLOYER just to be safe
            registrationDto.setRole("EMPLOYER");
            authService.registerUser(registrationDto);

            logger.info("Successfully registered employer with email: {}", registrationDto.getEmail());
            // Show success message and redirect via JS
            model.addAttribute("successMessage", "Registration Successful! You can now log in.");
            model.addAttribute("mode", "register");
            return "employer-register";
        } catch (Exception e) {
            logger.error("Employer Registration failed for email {}: {}", registrationDto.getEmail(), e.getMessage());
            model.addAttribute("error", e.getMessage());
            model.addAttribute("mode", "register");
            return "employer-register";
        }
    }
}
