package com.cv.domain.project.entity;

import com.cv.domain.skillStack.entity.SkillStack;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class ProjectSkillStack {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long projectSkillStackId;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "SKILL_STACK_ID")
    private SkillStack skillStack;
}
