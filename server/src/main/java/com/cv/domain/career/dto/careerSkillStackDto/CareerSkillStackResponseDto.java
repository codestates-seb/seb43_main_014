package com.cv.domain.career.dto.careerSkillStackDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CareerSkillStackResponseDto {
    @Schema(description = "기술 스택 식별자", example = "1")
    private long skillStackId;

    @Schema(description = "기술 스택 명", example = "Java")
    private String skillName;
}
