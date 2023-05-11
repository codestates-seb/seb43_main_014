package com.cv.domain.cv.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class CvSkillStackDto {

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
