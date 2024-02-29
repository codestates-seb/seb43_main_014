package com.cv.domain.cv.dto.cvDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CvSkillStackResponseDto {
    @Schema(description = "기술스택 식별자", example = "1")
    private long skillStackId;

    @Schema(description = "이력서에 작성할 기술 스택", example = "Java")
    private String skillName;
}
