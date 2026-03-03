package com.rev.app.revhire.rest;

import com.rev.app.revhire.dto.JobDto;
import com.rev.app.revhire.mapper.JobMapper;
import com.rev.app.revhire.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/jobs")
public class JobRestController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public List<JobDto> getAllJobs() {
        return jobService.getAllJobs().stream()
                .map(JobMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(JobMapper.toDto(jobService.getJobById(id)));
    }

    @GetMapping("/search")
    public List<JobDto> searchJobs(@RequestParam(required = false) String title,
                                   @RequestParam(required = false) String location,
                                   @RequestParam(required = false) Integer minExperience,
                                   @RequestParam(required = false) String companyName,
                                   @RequestParam(required = false) String jobType,
                                   @RequestParam(required = false) String minSalary,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate postedAfter) {
        return jobService.findJobs(title, location, minExperience, companyName, jobType, minSalary, postedAfter)
                .stream()
                .map(JobMapper::toDto)
                .collect(Collectors.toList());
    }
}
