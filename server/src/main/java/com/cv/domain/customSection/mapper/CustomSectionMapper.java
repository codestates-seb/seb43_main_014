package com.cv.domain.customSection.mapper;

import com.cv.domain.customSection.dto.CustomSectionDto;
import com.cv.domain.customSection.entity.CustomSection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomSectionMapper {
    CustomSection customSectionAddToCustomSection(CustomSectionDto.Add customSectionAdd);
    CustomSectionDto.Response customSectionToCustomSectionResponse(CustomSection customSection);
    List<CustomSection> customSectionAddsToCustomSections(List<CustomSectionDto.Add> customSectionAdds);
    List<CustomSectionDto.Response> customSectionsToCustomSectionResponses(List<CustomSection> customSections);
}
