package com.cv.domain.career.repository;

import com.cv.domain.career.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career, Long> {
}
