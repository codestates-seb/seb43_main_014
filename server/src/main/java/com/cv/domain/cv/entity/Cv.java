package com.cv.domain.cv.entity;

import com.cv.domain.career.entity.Career;
import com.cv.domain.customSection.entity.CustomSection;
import com.cv.domain.education.entity.Education;
import com.cv.domain.project.entity.Project;
import com.cv.domain.user.entity.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

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

    public void setCvSkillStacks(List<CvSkillStack> cvSkillStacks){
        this.cvSkillStacks = cvSkillStacks;
        if(cvSkillStacks == null)
            return;
        for(CvSkillStack css : cvSkillStacks){
            if(css.getCv() != this){
                css.setCv(this);
            }
        }
    }
    public void setEducations(List<Education> educations){
        this.educations = educations;
        if(educations == null)
            return;
        for(Education e : educations){
            if(e.getCv() != this){
                e.setCv(this);
            }
        }
    }
    public void setCustomSections(List<CustomSection> customSections){
        this.customSections = customSections;
        if(customSections == null)
            return;
        for(CustomSection cs : customSections){
            if(cs.getCv() != this){
                cs.setCv(this);
            }
        }
    }
    public void setProjects(List<Project> projects){
        this.projects = projects;
        if(projects == null)
            return;
        for(Project p : projects){
            if(p.getCv() != this){
                p.setCv(this);
            }
        }
    }
    public void setCareers(List<Career> careers){
        this.careers = careers;
        if(careers == null)
            return;
        for(Career c : careers){
            if(c.getCv() != this){
                c.setCv(this);
            }
        }
    }
    public void setLinks(List<Link> links){
        this.links = links;
        if(links == null)
            return;
        for(Link l : links){
            if(l.getCv() != this){
                l.setCv(this);
            }
        }
    }
    public void setPortfolios(List<Portfolio> portfolios){
        this.portfolios = portfolios;
        if(portfolios == null)
            return;
        for(Portfolio p : portfolios){
            if(p.getCv() != this){
                p.setCv(this);
            }
        }
    }
}
