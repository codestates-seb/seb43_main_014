package com.cv.domain.skillStack.repository;

import com.cv.domain.skillStack.entity.SkillStack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillStackRepository extends JpaRepository<SkillStack, Long> {

    Optional<SkillStack> findBySkillName(String skillName);
}