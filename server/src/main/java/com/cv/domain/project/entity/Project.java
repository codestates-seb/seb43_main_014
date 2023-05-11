package com.cv.domain.project.entity;

import com.cv.domain.cv.entity.Cv;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Project {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long projectId;

    private String startYear;

    private String startMonth;

    private String endYear;

    private String endMonth;

    private String projectSubject;

    @Lob
    private String description;

    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CV_ID")
    private Cv cv;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectSkillStack> projectSkillStacks = new ArrayList<>();
}
