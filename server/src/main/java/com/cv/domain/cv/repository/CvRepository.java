package com.cv.domain.cv.repository;

import com.cv.domain.cv.entity.Cv;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CvRepository extends JpaRepository<Cv, Long> {
    @Query(value = "select cv from Cv cv where cv.user.id = :userId and cv.isDelete = false")
    Page<Cv> findByUserIdFromRecently (@Param("userId") Long userId, Pageable pageable);

}
