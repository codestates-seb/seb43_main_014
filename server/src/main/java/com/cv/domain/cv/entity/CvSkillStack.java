package com.cv.domain.cv.entity;

import com.cv.domain.skillStack.entity.SkillStack;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class CvSkillStack {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long cvSkillStackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SKILL_STACK_ID", nullable = false)
    private SkillStack skillStack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CV_ID", nullable = false)
    private Cv cv;
}
