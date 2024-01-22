package com.cv.domain.project.dto.projectDto;

import com.cv.domain.project.dto.projectSkillStackDto.ProjectSkillStackAddDto;
import lombok.Data;

import java.util.List;

@Data
public class ProjectAddDto {
    private String part;

    private String startYear;

    private String startMonth;

    private String endYear;

    private String endMonth;

    private String projectSubject;

    private String description;

    private String link;

    private List<ProjectSkillStackAddDto> projectSkillStacks;
}
