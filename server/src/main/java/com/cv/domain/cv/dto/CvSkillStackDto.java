package com.cv.domain.cv.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class CvSkillStackDto {

    @Data
    public static class Add {
        private long skillStackId;
    }

    @Data
    public static class Response {
        @Schema(description = "기술 스택 식별자", example = "HTML")
        private long skillStackId;

        private String skillName;
    }
}
