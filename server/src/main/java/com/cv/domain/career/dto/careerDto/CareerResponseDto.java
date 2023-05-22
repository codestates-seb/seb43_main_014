package com.cv.domain.career.dto.careerDto;

import com.cv.domain.career.dto.careerSkillStackDto.CareerSkillStackResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class CareerResponseDto {
    @Schema(description = "경력 사항 식별자", example = "1")
    private long careerId;

    @Schema(description = "입사 연도", example = "1999")
    private String joinYear;

    @Schema(description = "입사 월", example = "2")
    private String joinMonth;

    @Schema(description = "퇴사 연도", example = "2003")
    private String retirementYear;

    @Schema(description = "퇴사 월", example = "2")
    private String retirementMonth;

    @Schema(description = "회사명", example = "코딩기업")
    private String companyName;

    @Schema(description = "직책", example = "주니어")
    private String duty;

    @Schema(description = "직무", example = "풀스택")
    private String developmentJob;

    @Schema(description = "설명", example = "설명")
    private String description;

    @Schema(description = "경력 사항에 작성할 기술 스택")
    private List<CareerSkillStackResponseDto> careerSkillStacks;
}
