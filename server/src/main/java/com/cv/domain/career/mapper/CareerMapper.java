package com.cv.domain.career.mapper;

import com.cv.domain.career.dto.careerDto.CareerAddDto;
import com.cv.domain.career.dto.careerDto.CareerResponseDto;
import com.cv.domain.career.dto.careerSkillStackDto.CareerSkillStackAddDto;
import com.cv.domain.career.dto.careerSkillStackDto.CareerSkillStackResponseDto;
import com.cv.domain.career.entity.Career;
import com.cv.domain.career.entity.CareerSkillStack;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CareerMapper {
    Career careerAddToCareer(CareerAddDto careerAdd);
    CareerResponseDto careerToCareerResponse(Career career);
    List<CareerResponseDto> careersToCareerResponses(List<Career> careers);
    List<Career> careerAddsToCareers(List<CareerAddDto> careerAdds);
    @Mapping(source = "skillStackId", target = "skillStack.skillStackId")
    CareerSkillStack careerSkillStackAddToCareerSkillStack(CareerSkillStackAddDto careerSkillStackAdd);
    @Mapping(source = "skillStack.skillStackId", target = "skillStackId")
    @Mapping(source = "skillStack.skillName", target = "skillName")
    CareerSkillStackResponseDto careerSkillStackToCareerSkillStackResponse(CareerSkillStack careerSkillStack);
    List<CareerSkillStack> careerSkillStackAddsToCareerSkillStacks(List<CareerSkillStackAddDto> careerSkillStackAdds);
    List<CareerSkillStackResponseDto> careerSkillStacksToCareerSkillStackResponses(List<CareerSkillStack> careerSkillStacks);
}
