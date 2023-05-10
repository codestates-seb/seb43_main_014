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

    private String imageUrl;

    private String birthYear;

    private String birthMonth;

    private String birthDay;

    private Boolean isDelete = false;

    @OneToMany(mappedBy = "cv", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<CvSkillStack> cvSkillStacks = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<CustomSection> customSections = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Career> careers = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Link> links = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Portfolio> portfolios = new ArrayList<>();

//    public void setCvSkillStacks(CvSkillStack cvSkillStack){
//        this.cvSkillStacks.add(cvSkillStack);
//        if(cvSkillStack.getCv() != this){
//            cvSkillStack.setCv(this);
//        }
//    }
//    public void setEducations(Education education){
//        this.educations.add(education);
//        if(education.getCv() != this){
//            education.setCv(this);
//        }
//    }
//    public void setCustomSections(CustomSection customSection){
//        this.customSections.add(customSection);
//        if(customSection.getCv() != this){
//            customSection.setCv(this);
//        }
//    }
//    public void setProjects(Project project){
//        this.projects.add(project);
//        if(project.getCv() != this){
//            project.setCv(this);
//        }
//    }
//    public void setCareers(Career career){
//        this.careers.add(career);
//        if(career.getCv() != this){
//            career.setCv(this);
//        }
//    }
//    public void setLinks(Link link){
//        this.links.add(link);
//        if(link.getCv() != this){
//            link.setCv(this);
//        }
//    }
//    public void setPortfolios(Portfolio portfolio){
//        this.portfolios.add(portfolio);
//        if(portfolio.getCv() != this){
//            portfolio.setCv(this);
//        }
//    }
}
