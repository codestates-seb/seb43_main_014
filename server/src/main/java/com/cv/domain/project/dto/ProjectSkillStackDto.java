package com.cv.domain.project.dto;

import lombok.Data;

public class ProjectSkillStackDto {

    @Data
    public static class Add {
        private long skillStackId;
    }

    @Data
    public static class Response {
        private long skillStackId;

        private String skillName;
    }
}
