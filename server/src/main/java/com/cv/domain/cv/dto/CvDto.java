package com.cv.domain.cv.dto;

import com.cv.domain.customSection.dto.CustomSectionDto;
import com.cv.domain.education.dto.EducationDto;
import com.cv.domain.career.dto.CareerDto;
import com.cv.domain.project.dto.ProjectDto;
import lombok.Data;

import java.util.List;

public class CvDto {

    @Data
    public static class Post {
        private long userId;

        private String title;

        private String name;

        private String email;

        private String phone;

        private String imageUrl;

        private String address;

        private String birthDay;

        private String birthMonth;

        private String birthYear;

        private String selfIntroduction;

        private String developmentJob;

        private List<CvSkillStackDto.Add> cvSkillStacks;

        private List<EducationDto.Add> educations;

        private List<CareerDto.Add> careers;

        private List<CustomSectionDto.Add> customSections;

        private List<ProjectDto.Add> projects;

        private List<LinkDto.Add> links;

        private List<PortfolioDto.Add> portfolios;
    }

    @Data
    public static class Patch {
        private long cvId;

        private String title;

        private String name;

        private String email;

        private String phone;

        private String imageUrl;

        private String address;

        private String birthDay;

        private String birthMonth;

        private String birthYear;

        private String selfIntroduction;

        private String developmentJob;

        private List<CvSkillStackDto.Add> cvSkillStacks;

        private List<EducationDto.Add> educations;

        private List<CareerDto.Add> careers;

        private List<CustomSectionDto.Add> customSections;

        private List<ProjectDto.Add> projects;

        private List<LinkDto.Add> links;

        private List<PortfolioDto.Add> portfolios;
    }

    @Data
    public static class Response {
        private Long cvId;

        private String title;

        private long userId;

        private String name;

        private String email;

        private String imageUrl;

        private String phone;

        private String address;

        private String birthDay;

        private String birthMonth;

        private String birthYear;

        private String selfIntroduction;

        private String developmentJob;

        private Boolean isDelete;

        private List<CvSkillStackDto.Response> cvSkillStacks;

        private List<EducationDto.Response> educations;

        private List<CareerDto.Response> careers;

        private List<CustomSectionDto.Response> customSections;

        private List<ProjectDto.Response> projects;

        private List<LinkDto.Response> links;

        private List<PortfolioDto.Response> portfolios;
    }
}
