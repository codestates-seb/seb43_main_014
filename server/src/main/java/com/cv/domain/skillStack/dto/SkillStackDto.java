package com.cv.domain.skillStack.dto;

import lombok.Getter;

public class SkillStackDto {

    @Getter
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
