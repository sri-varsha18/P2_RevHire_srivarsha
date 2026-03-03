package com.rev.app.revhire.rest;

import com.rev.app.revhire.entity.JobSeeker;
import com.rev.app.revhire.entity.Resume;
import com.rev.app.revhire.entity.User;
import com.rev.app.revhire.repository.JobSeekerRepository;
import com.rev.app.revhire.repository.UserRepository;
import com.rev.app.revhire.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/resumes")
public class ResumeRestController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadResume(@RequestParam("file") MultipartFile file,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        if (file.getSize() > 2 * 1024 * 1024) {
            return ResponseEntity.badRequest().body("File size exceeds 2MB limit");
        }
        String contentType = file.getContentType();
        if (contentType == null || (!contentType.equals("application/pdf") &&
                !contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
            return ResponseEntity.badRequest().body("Only PDF and DOCX formats are allowed");
        }

        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        JobSeeker seeker = jobSeekerRepository.findByUser(user).orElseThrow();

        try {
            Resume saved = resumeService.uploadResume(seeker, file);
            return ResponseEntity.ok("File uploaded successfully: " + saved.getResumeFileName());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to upload file: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resume> updateTextualResume(@PathVariable Long id, @RequestBody Resume resumeDetails) {
        return ResponseEntity.ok(resumeService.updateResume(id, resumeDetails));
    }
}
