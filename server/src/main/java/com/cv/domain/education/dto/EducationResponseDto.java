package com.cv.domain.education.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EducationResponseDto {
    @Schema(description = "교육 사항 식별자", example = "1")
    private long educationId;

    @Schema(description = "학위", example = "학사")
    private String degree;

    @Schema(description = "전공", example = "코딩학과")
    private String major;

    @Schema(description = "학교 명", example = "코딩대학교")
    private String schoolName;

    @Schema(description = "입학 월", example = "1")
    private String admissionMonth;

    @Schema(description = "입학 연도", example = "2000")
    private String admissionYear;

    @Schema(description = "졸업 월", example = "1")
    private String graduationMonth;

    @Schema(description = "졸업 연도", example = "2004")
    private String graduationYear;

    @Schema(description = "설명", example = "설명")
    private String description;
}
