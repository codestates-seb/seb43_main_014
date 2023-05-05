package com.cv.domain.skillStack.entity;

import com.cv.domain.cv.entity.CvSkillStack;
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

    private String description;

    @OneToMany(mappedBy = "skill_stack", cascade = CascadeType.REMOVE)
    private List<CvSkillStack> cvSkillStackList = new ArrayList<>();
}
