package com.cv.domain.skillStack.entity;

import com.cv.domain.career.entity.CareerSkillStack;
import com.cv.domain.cv.entity.CvSkillStack;
import com.cv.domain.project.entity.ProjectSkillStack;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class SkillStack {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long skillStackId;

    private String skillName;

    private String description;

    @OneToMany(mappedBy = "skillStack", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<CvSkillStack> cvSkillStacks = new ArrayList<>();

    @OneToMany(mappedBy = "skillStack", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<ProjectSkillStack> projectSkillStacks = new ArrayList<>();

    @OneToMany(mappedBy = "skillStack", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<CareerSkillStack> careerSkillStacks = new ArrayList<>();
}
