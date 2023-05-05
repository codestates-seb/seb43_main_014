package com.cv.domain.customSection.dto;

import lombok.Data;
import lombok.Getter;

public class CustomSectionDto {

    @Data
    public static class Add {
        private String customName;

        private String customContent;
    }

    @Getter
    public static class Response {
        private long customSectionId;

        private String customName;

        private String customContent;
    }
}
