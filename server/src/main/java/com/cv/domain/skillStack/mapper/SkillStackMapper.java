package com.cv.domain.skillStack.mapper;

import com.cv.domain.skillStack.dto.SkillStackDto;
import com.cv.domain.skillStack.entity.SkillStack;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillStackMapper {

    SkillStack skillStackPostToSkillStack(SkillStackDto.Add add);
    SkillStackDto.Response skillStackToSkillStackResponse(SkillStack skillStack);
    List<SkillStack> skillStackAddsToSkillStacks(List<SkillStackDto.Add> SkillStackAdds);
    List<SkillStackDto.Response> skillStacksToSkillStackResponses(List<SkillStack> skillStacks);
}
