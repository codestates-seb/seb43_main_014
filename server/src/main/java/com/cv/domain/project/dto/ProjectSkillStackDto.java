package com.cv.domain.project.dto;

import lombok.Data;
import lombok.Getter;

public class ProjectSkillStackDto {

    @Data
    public static class Add {
        private String skillName;

        private String description;
    }

    @Getter
    public static class Response {
        private long skillStackId;

        private String skillName;

        private String description;
    }
}
