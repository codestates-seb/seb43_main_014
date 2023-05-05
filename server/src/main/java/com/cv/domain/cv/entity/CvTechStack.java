package com.cv.domain.cv.entity;

import com.cv.domain.skillStack.entity.SkillStack;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class CvTechStack {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long cvTechStackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SKILL_STACK_ID", nullable = false)
    private SkillStack skillStack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CV_ID", nullable = false)
    private Cv cv;
}
