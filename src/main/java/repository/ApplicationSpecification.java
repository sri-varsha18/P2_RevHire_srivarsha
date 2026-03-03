package com.rev.app.revhire.repository;

import com.rev.app.revhire.entity.Application;
import com.rev.app.revhire.entity.Job;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApplicationSpecification {
    public static Specification<Application> filterApplications(Job job, String status, Integer minExperience,
                                                                String skills) {
        return filterApplications(job, status, minExperience, skills, null, null);
    }

    public static Specification<Application> filterApplications(Job job, String status, Integer minExperience,
                                                                String skills, String education, LocalDate appliedAfter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (job != null) {
                predicates.add(cb.equal(root.get("job"), job));
            }
            if (status != null && !status.isEmpty()) {
                predicates.add(cb.equal(cb.upper(root.get("status")), status.toUpperCase()));
            }
            if (minExperience != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("jobSeeker").get("experienceYears"), minExperience));
            }
            if (skills != null && !skills.isEmpty()) {
                predicates
                        .add(cb.like(cb.lower(root.get("jobSeeker").get("skills")), "%" + skills.toLowerCase() + "%"));
            }
            if (education != null && !education.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("jobSeeker").get("education")),
                        "%" + education.toLowerCase() + "%"));
            }
            if (appliedAfter != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("appliedDate"), appliedAfter));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
