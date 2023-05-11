package com.cv.domain.project.mapper;

import com.cv.domain.project.dto.ProjectDto;
import com.cv.domain.project.dto.ProjectSkillStackDto;
import com.cv.domain.project.entity.Project;
import com.cv.domain.project.entity.ProjectSkillStack;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project projectAddToProject(ProjectDto.Add projectAdd);
    ProjectDto.Response projectToProjectResponse(Project project);
    List<Project> projectAddsToProjects(List<ProjectDto.Add> projectAdds);
    List<ProjectDto.Response> projectsToProjectResponses(List<Project> projects);
    @Mapping(source = "skillStackId", target = "skillStack.skillStackId")
    ProjectSkillStack projectSkillStackAddToProjectSkillStack(ProjectSkillStackDto.Add projectSkillStackAdd);
    @Mapping(source = "skillStack.skillStackId", target = "skillStackId")
    @Mapping(source = "skillStack.skillName", target = "skillName")
    ProjectSkillStackDto.Response projectSkillStackToProjectSkillStackResponse(ProjectSkillStack projectSkillStack);
    List<ProjectSkillStack> projectSkillStackAddsToProjectSkillStacks(List<ProjectSkillStackDto.Add> projectSkillStackAdds);
    List<ProjectSkillStackDto.Response> projectSkillStacksToProjectSkillStackResponses(List<ProjectSkillStack> projectSkillStacks);
}
