package com.cv.domain.education.mapper;

import com.cv.domain.education.dto.EducationDto;
import com.cv.domain.education.entity.Education;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationMapper {
    Education educationAddToEducation(EducationDto.Add educationAdd);
    EducationDto.Response educationToEducationResponse(Education education);
    List<Education> educationAddsToEducations(List<EducationDto.Add> educationAdds);
    List<EducationDto.Response> educationsToEducationResponses(List<Education> educations);
}
