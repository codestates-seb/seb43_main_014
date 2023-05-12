package com.cv.domain.skillStack.dto;

import lombok.Data;

public class SkillStackDto {

    @Data
    public static class Add {
        private String skillName;

        private String description;
    }

    @Data
    public static class Response {
        private long skillStackId;

        private String skillName;

        private String description;
    }
}
