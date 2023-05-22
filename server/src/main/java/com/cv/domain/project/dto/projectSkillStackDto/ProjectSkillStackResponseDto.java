package com.cv.domain.project.dto.projectSkillStackDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProjectSkillStackResponseDto {
    @Schema(description = "기술 스택 식별자", example = "1")
    private long skillStackId;

    @Schema(description = "기술 스택 명", example = "Java")
    private String skillName;
}
