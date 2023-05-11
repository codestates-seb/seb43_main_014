package com.cv.domain.career.mapper;

import com.cv.domain.career.dto.CareerDto;
import com.cv.domain.career.dto.CareerSkillStackDto;
import com.cv.domain.career.entity.Career;
import com.cv.domain.career.entity.CareerSkillStack;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CareerMapper {
    Career careerAddToCareer(CareerDto.Add careerAdd);
    CareerDto.Response careerToCareerResponse(Career career);
    List<CareerDto.Response> careersToCareerResponses(List<Career> careers);
    List<Career> careerAddsToCareers(List<CareerDto.Add> careerAdds);
    @Mapping(source = "skillStackId", target = "skillStack.skillStackId")
    CareerSkillStack careerSkillStackAddToCareerSkillStack(CareerSkillStackDto.Add careerSkillStackAdd);
    @Mapping(source = "skillStack.skillStackId", target = "skillStackId")
    @Mapping(source = "skillStack.skillName", target = "skillName")
    CareerSkillStackDto.Response careerSkillStackToCareerSkillStackResponse(CareerSkillStack careerSkillStack);
    List<CareerSkillStack> careerSkillStackAddsToCareerSkillStacks(List<CareerSkillStackDto.Add> careerSkillStackAdds);
    List<CareerSkillStackDto.Response> careerSkillStacksToCareerSkillStackResponses(List<CareerSkillStack> careerSkillStacks);
}
