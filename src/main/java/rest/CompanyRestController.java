package com.rev.app.revhire.rest;

import com.rev.app.revhire.dto.CompanyDto;
import com.rev.app.revhire.mapper.CompanyMapper;
import com.rev.app.revhire.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
public class CompanyRestController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<CompanyDto> getAllCompanies() {
        return companyService.getAllCompanies().stream()
                .map(CompanyMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Long id) {
        return ResponseEntity.ok(CompanyMapper.toDto(companyService.getCompanyById(id)));
    }
}
