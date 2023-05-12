package com.cv.domain.career.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class CareerSkillStackDto {

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
