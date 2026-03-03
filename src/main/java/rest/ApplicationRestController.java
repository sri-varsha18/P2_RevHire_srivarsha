package com.rev.app.revhire.rest;

import com.rev.app.revhire.dto.ApplicationDto;
import com.rev.app.revhire.entity.Job;
import com.rev.app.revhire.mapper.ApplicationMapper;
import com.rev.app.revhire.service.ApplicationService;
import com.rev.app.revhire.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/applications")
public class ApplicationRestController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private JobService jobService;

    @GetMapping("/search")
    public List<ApplicationDto> search(@RequestParam Long jobId,
                                       @RequestParam(required = false) String status,
                                       @RequestParam(required = false) Integer minExperience,
                                       @RequestParam(required = false) String skills) {
        Job job = jobService.getJobById(jobId);
        return applicationService.findApplications(job, status, minExperience, skills).stream()
                .map(ApplicationMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDto> getApplication(@PathVariable Long id) {
        return ResponseEntity.ok(ApplicationMapper.toDto(applicationService.getApplicationById(id)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApplicationDto> updateStatus(@PathVariable Long id,
                                                       @RequestParam String status,
                                                       @RequestParam(required = false) String notes) {
        return ResponseEntity
                .ok(ApplicationMapper.toDto(applicationService.updateApplicationStatus(id, status, notes)));
    }

    @PostMapping("/bulk-status")
    public ResponseEntity<Void> bulkUpdateStatus(@RequestBody List<Long> ids,
                                                 @RequestParam String status,
                                                 @RequestParam(required = false) String notes) {
        applicationService.bulkUpdateStatus(ids, status, notes);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> withdraw(@PathVariable Long id, @RequestParam String reason) {
        applicationService.withdrawApplication(id, reason);
        return ResponseEntity.noContent().build();
    }
}
