package com.cv.domain.cv.repository;

import com.cv.domain.cv.entity.Cv;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CvRepository extends JpaRepository<Cv, Long> {
    // JPQL - 엔티티 객체를 대상으로 질의하는 쿼리
    // 특정 사용자, 최신순으로 이력서 조회, 삭제된 이력서는 리스트에서 제외
    @Query(value = "select cv from Cv cv where cv.user.userId = :userId and cv.isDelete = false")
    Page<Cv> findByUserIdFromRecently (@Param("userId") Long userId, Pageable pageable); // (1)

}
