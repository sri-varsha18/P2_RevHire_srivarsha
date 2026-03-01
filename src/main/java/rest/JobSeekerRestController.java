package com.rev.app.revhire.rest;

import com.rev.app.revhire.dto.JobSeekerDto;
import com.rev.app.revhire.mapper.JobSeekerMapper;
import com.rev.app.revhire.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekerRestController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<JobSeekerDto> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(JobSeekerMapper.toDto(profileService.getJobSeekerById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSeekerDto> updateProfile(@PathVariable Long id, @Valid @RequestBody JobSeekerDto dto) {
        // Implementation would involve combining entity retrieval and mapper
        // For now, let's assume the service handle the update logic
        // and return the updated entity
        return ResponseEntity.ok(
                JobSeekerMapper.toDto(profileService.updateJobSeekerProfile(id, JobSeekerMapper.toEntity(dto, null))));
    }
}
