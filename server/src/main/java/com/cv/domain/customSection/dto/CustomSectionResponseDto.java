package com.cv.domain.customSection.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CustomSectionResponseDto {
    @Schema(description = "사용자 정의 사항 식별자", example = "1")
    private long customSectionId;

    @Schema(description = "사용자 정의 사항 명", example = "사용자 정의 사항")
    private String customName;

    @Schema(description = "사용자 정의 사항 내용", example = "이런 대회에서 수상한 경험이 있습니다.")
    private String customContent;
}
