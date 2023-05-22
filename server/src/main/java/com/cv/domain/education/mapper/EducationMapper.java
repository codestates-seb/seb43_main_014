package com.cv.domain.education.mapper;

import com.cv.domain.education.dto.EducationAddDto;
import com.cv.domain.education.dto.EducationResponseDto;
import com.cv.domain.education.entity.Education;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationMapper {
    Education educationAddToEducation(EducationAddDto educationAdd);
    EducationResponseDto educationToEducationResponse(Education education);
    List<Education> educationAddsToEducations(List<EducationAddDto> educationAdds);
    List<EducationResponseDto> educationsToEducationResponses(List<Education> educations);
}
