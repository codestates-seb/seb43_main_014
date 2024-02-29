package com.cv.domain.education.repository;

import com.cv.domain.education.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
}
