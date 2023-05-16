package com.cv.domain.education.dto;

import lombok.Data;

public class EducationDto {

    @Data
    public static class Add {
        private String degree;

        private String major;

        private String schoolName;

        private String admissionMonth;

        private String admissionYear;

        private String graduationMonth;

        private String graduationYear;

        private String description;
    }

    @Data
    public static class Response {
        private long educationId;

        private String degree;

        private String major;

        private String schoolName;

        private String admissionMonth;

        private String admissionYear;

        private String graduationMonth;

        private String graduationYear;

        private String description;
    }
}
