package com.cv.domain.career.dto;

import lombok.Data;

public class CareerSkillStackDto {

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
