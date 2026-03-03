package com.rev.app.revhire.repository;

import com.rev.app.revhire.entity.Job;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class JobSpecification {
    public static Specification<Job> filterJobs(String title, String location, Integer experience,
                                                String companyName, String jobType, String salaryRange, java.time.LocalDate postedAfter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isEmpty()) {
                String searchStr = "%" + title.toLowerCase() + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("title")), searchStr),
                        cb.like(cb.lower(root.get("skillsRequired")), searchStr)));
            }
            if (location != null && !location.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("location")), "%" + location.toLowerCase() + "%"));
            }
            if (experience != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("experienceRequired"), experience));
            }
            if (companyName != null && !companyName.isEmpty()) {
                predicates
                        .add(cb.like(cb.lower(root.get("company").get("name")), "%" + companyName.toLowerCase() + "%"));
            }
            if (jobType != null && !jobType.isEmpty()) {
                predicates.add(cb.equal(root.get("jobType"), jobType));
            }
            if (salaryRange != null && !salaryRange.isEmpty()) {
                predicates.add(cb.like(root.get("salaryRange"), "%" + salaryRange + "%"));
            }
            if (postedAfter != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), postedAfter.atStartOfDay()));
            }

            predicates.add(cb.equal(root.get("status"), "OPEN"));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
