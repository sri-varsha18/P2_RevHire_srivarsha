package com.rev.app.revhire.service;

import com.rev.app.revhire.entity.Company;
import com.rev.app.revhire.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
    }

    @Transactional
    public Company saveCompany(Company company) {
        Company saved = companyRepository.save(company);
        companyRepository.flush();
        return saved;
    }
}
