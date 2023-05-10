package com.cv.domain.career.entity;

import com.cv.domain.cv.entity.Cv;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Career {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long careerId;

    private String joinYear;

    private String joinMonth;

    private String retirementYear;

    private String retirementMonth;

    private String companyName;

    private String duty;    // 직책

    private String developmentJob;

    @Lob
    private String description;

    @OneToMany(mappedBy = "career")
    private List<CareerSkillStack> careerSkillStacks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CV_ID")
    private Cv cv;
}
