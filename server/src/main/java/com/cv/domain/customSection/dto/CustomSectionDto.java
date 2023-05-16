package com.cv.domain.customSection.dto;

import lombok.Data;

public class CustomSectionDto {

    @Data
    public static class Add {
        private String customName;

        private String customContent;
    }

    @Data
    public static class Response {
        private long customSectionId;

        private String customName;

        private String customContent;
    }
}
