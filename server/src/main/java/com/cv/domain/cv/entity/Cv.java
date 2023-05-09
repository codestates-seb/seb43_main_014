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

    //    TODO : profileImage 추가 -> 검색
    //    TODO : myLinks 추가 -> Dto 수정해야함
    //    TODO : portfolio 추가
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long cvId;

    private String email;

    private String name;

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
    private List<CvSkillStack> cvSkillStacks = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<CustomSection> customSections = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<Career> careers = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.REMOVE)
    private List<Link> links = new ArrayList<>();
}
