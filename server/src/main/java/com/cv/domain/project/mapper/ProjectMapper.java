package com.cv.domain.project.mapper;

import com.cv.domain.project.dto.projectDto.ProjectAddDto;
import com.cv.domain.project.dto.projectDto.ProjectResponseDto;
import com.cv.domain.project.dto.projectSkillStackDto.ProjectSkillStackAddDto;
import com.cv.domain.project.dto.projectSkillStackDto.ProjectSkillStackResponseDto;
import com.cv.domain.project.entity.Project;
import com.cv.domain.project.entity.ProjectSkillStack;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project projectAddToProject(ProjectAddDto projectAdd);
    ProjectResponseDto projectToProjectResponse(Project project);
    List<Project> projectAddsToProjects(List<ProjectAddDto> projectAdds);
    List<ProjectResponseDto> projectsToProjectResponses(List<Project> projects);
    @Mapping(source = "skillStackId", target = "skillStack.skillStackId")
    ProjectSkillStack projectSkillStackAddToProjectSkillStack(ProjectSkillStackAddDto projectSkillStackAdd);
    @Mapping(source = "skillStack.skillStackId", target = "skillStackId")
    @Mapping(source = "skillStack.skillName", target = "skillName")
    ProjectSkillStackResponseDto projectSkillStackToProjectSkillStackResponse(ProjectSkillStack projectSkillStack);
    List<ProjectSkillStack> projectSkillStackAddsToProjectSkillStacks(List<ProjectSkillStackAddDto> projectSkillStackAdds);
    List<ProjectSkillStackResponseDto> projectSkillStacksToProjectSkillStackResponses(List<ProjectSkillStack> projectSkillStacks);
}
