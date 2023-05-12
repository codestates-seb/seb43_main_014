package com.cv.domain.project.dto;

import lombok.Data;
import lombok.Getter;

public class ProjectSkillStackDto {

    @Data
    public static class Add {
        private long skillStackId;

//        private String description;
    }

    @Data
    public static class Response {
        private long skillStackId;

        private String skillName;

//        private String description;
    }
}
