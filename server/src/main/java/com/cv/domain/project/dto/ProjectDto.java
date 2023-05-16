package com.cv.domain.project.dto;

import lombok.Data;

import java.util.List;

public class ProjectDto {

    @Data
    public static class Add {
        private String part;

        private String startYear;

        private String startMonth;

        private String endYear;

        private String endMonth;

        private String projectSubject;

        private String description;

        private String link;

        private List<ProjectSkillStackDto.Add> projectSkillStacks;
    }

    @Data
    public static class Response {
        private long projectId;

        private String part;

        private String startYear;

        private String startMonth;

        private String endYear;

        private String endMonth;

        private String projectSubject;

        private String description;

        private String link;

        private List<ProjectSkillStackDto.Response> projectSkillStacks;
    }
}
