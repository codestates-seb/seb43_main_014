package com.cv.domain.project.entity;

import com.cv.domain.cv.entity.Cv;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
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

    private String projectName;

    @Lob
    private String description;

    // TODO : links 추가

    @ManyToOne
    @JoinColumn(name = "CV_ID", nullable = false)
    private Cv cv;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private List<ProjectSkillStack> projectSkillStackList = new ArrayList<>();
}
