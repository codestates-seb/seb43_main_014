package com.cv.domain.customSection.mapper;

import com.cv.domain.customSection.dto.CustomSectionAddDto;
import com.cv.domain.customSection.dto.CustomSectionResponseDto;
import com.cv.domain.customSection.entity.CustomSection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomSectionMapper {
    CustomSection customSectionAddToCustomSection(CustomSectionAddDto customSectionAdd);
    CustomSectionResponseDto customSectionToCustomSectionResponse(CustomSection customSection);
    List<CustomSection> customSectionAddsToCustomSections(List<CustomSectionAddDto> customSectionAdds);
    List<CustomSectionResponseDto> customSectionsToCustomSectionResponses(List<CustomSection> customSections);
}
