package com.rev.app.revhire.service;

import com.rev.app.revhire.dto.UserRegistrationDto;
import com.rev.app.revhire.entity.*;
import com.rev.app.revhire.repository.CompanyRepository;
import com.rev.app.revhire.repository.EmployerRepository;
import com.rev.app.revhire.repository.JobSeekerRepository;
import com.rev.app.revhire.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(UserRegistrationDto registrationDto) {
        String email = registrationDto.getEmail().trim().toLowerCase();
        String role = registrationDto.getRole() != null ? registrationDto.getRole().trim().toUpperCase() : "JOBSEEKER";

        logger.info("Attempting to register user with email: {} and role: {}", email, role);

        if (userRepository.existsByEmail(email)) {
            logger.warn("Registration failed: Email {} already exists", email);
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .role(role)
                .build();

        logger.debug("Saving user entity for email: {}", email);
        user = userRepository.save(user);
        userRepository.flush();
        logger.info("User entity saved and flushed with ID: {}", user.getUserId());

        if ("JOBSEEKER".equalsIgnoreCase(role)) {
            logger.debug("Creating JobSeeker profile for user ID: {}", user.getUserId());
            com.rev.app.revhire.entity.JobSeeker jobSeeker = com.rev.app.revhire.entity.JobSeeker.builder()
                    .user(user)
                    .name(registrationDto.getName())
                    .phone(registrationDto.getPhone())
                    .location(registrationDto.getLocation())
                    .employmentStatus(registrationDto.getEmploymentStatus())
                    .profileCompletion(0)
                    .build();
            jobSeekerRepository.save(jobSeeker);
            jobSeekerRepository.flush();
            logger.info("JobSeeker profile created and flushed for email: {}", email);
        } else if ("EMPLOYER".equalsIgnoreCase(role)) {
            logger.debug("Creating Employer/Company profile for user ID: {}", user.getUserId());
            com.rev.app.revhire.entity.Company company = null;
            if (registrationDto.getCompanyName() != null) {
                company = com.rev.app.revhire.entity.Company.builder()
                        .name(registrationDto.getCompanyName())
                        .industry(registrationDto.getIndustry())
                        .size(registrationDto.getCompanySize())
                        .description(registrationDto.getCompanyDescription())
                        .website(registrationDto.getCompanyWebsite())
                        .location(registrationDto.getLocation())
                        .build();
                company = companyRepository.save(company);
                logger.debug("Company profile created with ID: {}", company.getCompanyId());
            }

            com.rev.app.revhire.entity.Employer employer = com.rev.app.revhire.entity.Employer.builder()
                    .user(user)
                    .company(company)
                    .build();
            employerRepository.save(employer);
            logger.info("Employer profile created for email: {}", email);
        }

        return user;
    }
}
