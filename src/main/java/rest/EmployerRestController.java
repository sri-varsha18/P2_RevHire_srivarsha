package com.rev.app.revhire.rest;

import com.rev.app.revhire.dto.EmployerDto;
import com.rev.app.revhire.mapper.EmployerMapper;
import com.rev.app.revhire.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employers")
public class EmployerRestController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployerDto> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(EmployerMapper.toDto(profileService.getEmployerById(id)));
    }
}
