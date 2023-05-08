package com.cv.domain.career.entity;

import com.cv.domain.skillStack.entity.SkillStack;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class CareerSkillStack {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long careerSkillStackId;

    @ManyToOne
    @JoinColumn(name = "CAREER_ID", nullable = false)
    private Career career;

    @ManyToOne
    @JoinColumn(name = "SKILL_STACK_ID", nullable = false)
    private SkillStack skillStack;
}