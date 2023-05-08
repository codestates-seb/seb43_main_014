package com.cv.domain.project.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

public class ProjectDto {

    @Data
    public static class Add {
        private String startYear;

        private String startMonth;

        private String endYear;

        private String endMonth;

        private String projectSubject;

        private List<ProjectSkillStackDto.Add> skillList;
    }

    @Getter
    public static class Response {
        private long projectId;

        private String startYear;

        private String startMonth;

        private String endYear;

        private String endMonth;

        private String projectSubject;

        private List<ProjectSkillStackDto.Add> skillList;
    }
}
