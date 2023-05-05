package com.cv.domain.cv.entity;

import com.cv.domain.career.entity.Career;
import com.cv.domain.customSection.entity.CustomSection;
import com.cv.domain.education.entity.Education;
import com.cv.domain.project.entity.Project;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Cv {

    //    TODO : profileImage 추가
    //    TODO : myLinks 추가
    //    TODO : portfolio 추가
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long cvId;

    private String email;

    private String userName;

    private String address;

    private String phone;

    @Lob
    private String selfIntroduction;

    private String developmentJob;

    private String birthYear;

    private String birthMonth;

    private String birthDay;

    private Boolean isDelete = false;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<CvSkillStack> cvSkillStackList = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<Education> educationList = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<CustomSection> customSectionList = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<Project> projectList = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<Career> careerList = new ArrayList<>();
}
