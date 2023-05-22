package com.cv.domain.education.dto;

import lombok.Data;

@Data
public class EducationAddDto {
    private String degree;

    private String major;

    private String schoolName;

    private String admissionMonth;

    private String admissionYear;

    private String graduationMonth;

    private String graduationYear;

    private String description;
}
