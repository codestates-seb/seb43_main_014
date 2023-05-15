package com.cv.domain.cv.repository;

import com.cv.domain.cv.entity.Cv;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvRepository extends JpaRepository<Cv, Long> {
    Page<Cv> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
}
