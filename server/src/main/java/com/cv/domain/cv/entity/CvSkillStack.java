package com.cv.domain.cv.entity;

import com.cv.domain.skillStack.entity.SkillStack;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CvSkillStack {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long cvSkillStackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SKILL_STACK_ID")
    private SkillStack skillStack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CV_ID")
    private Cv cv;
}