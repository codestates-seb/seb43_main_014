package com.cv.domain.cv.dto;

import com.cv.domain.customSection.dto.CustomSectionDto;
import com.cv.domain.education.dto.EducationDto;
import com.cv.domain.career.dto.CareerDto;
import com.cv.domain.project.dto.ProjectSkillStackDto;
import lombok.Data;
import lombok.Getter;

import java.util.List;

public class CvDto {

    @Getter
    public static class Post {

        private String name;

        private String email;

        private String phone;

        private String address;

        private String birthDay;

        private String birthMonth;

        private String birthYear;

        private String selfIntroduction;

        private String developmentJob;

        private List<CvSkillStackDto.Add> skillList;

        private List<EducationDto.Add> educationList;

        private List<CareerDto.Add> careerList;

        private List<CustomSectionDto.Add> customSectionList;

        private List<ProjectSkillStackDto.Add> projectList;
    }

    @Data
    public static class Patch {

    }

    @Getter
    public static class Response {
        private long cvId;

        private String name;

        private String email;

        private String phone;

        private String address;

        private String birthDay;

        private String birthMonth;

        private String birthYear;

        private String selfIntroduction;

        private String developmentJob;

        private List<CvSkillStackDto.Response> skillList;

        private List<EducationDto.Response> educationList;

        private List<CareerDto.Response> careerList;

        private List<CustomSectionDto.Response> customSectionList;

        private List<ProjectSkillStackDto.Response> projectList;
    }
}
