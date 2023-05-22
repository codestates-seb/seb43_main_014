package com.cv.domain.project.dto.projectDto;

import com.cv.domain.project.dto.projectSkillStackDto.ProjectSkillStackResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ProjectResponseDto {
    @Schema(description = "프로젝트 식별자", example = "1")
    private long projectId;

    @Schema(description = "프로젝트 역할", example = "팀장")
    private String part;

    @Schema(description = "프로젝트 시작 연도", example = "2023")
    private String startYear;

    @Schema(description = "프로젝트 시작 월", example = "5")
    private String startMonth;

    @Schema(description = "프로젝트 종료 연도", example = "2023")
    private String endYear;

    @Schema(description = "프로젝트 종료 월", example = "6")
    private String endMonth;

    @Schema(description = "프로젝트 주제", example = "이력서 자동완성 애플리케이션")
    private String projectSubject;

    @Schema(description = "프로젝트 설명", example = "이력서를 자동으로 만드러줌")
    private String description;

    @Schema(description = "프로젝트 링크", example = "kakao.com(링크)")
    private String link;

    @Schema(description = "프로젝트에 들어갈 기술 스택")
    private List<ProjectSkillStackResponseDto> projectSkillStacks;
}
