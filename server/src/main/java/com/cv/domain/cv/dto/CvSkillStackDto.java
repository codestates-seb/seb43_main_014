package com.cv.domain.cv.dto;

import lombok.Data;

public class CvSkillStackDto {

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
