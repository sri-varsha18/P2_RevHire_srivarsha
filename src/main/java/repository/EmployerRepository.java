package com.rev.app.revhire.repository;

import com.rev.app.revhire.entity.Employer;
import com.rev.app.revhire.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Optional<Employer> findByUser(User user);

    Optional<Employer> findByUser_Email(String email);

    List<Employer> findByCompany_CompanyId(Long companyId);
}
