package com.cv.domain.cv.mapper;

import com.cv.domain.career.mapper.CareerMapper;
import com.cv.domain.customSection.mapper.CustomSectionMapper;
import com.cv.domain.cv.dto.cvDto.*;
import com.cv.domain.cv.dto.linkDto.LinkAddDto;
import com.cv.domain.cv.dto.linkDto.LinkResponseDto;
import com.cv.domain.cv.dto.portfolioDto.PortfolioAddDto;
import com.cv.domain.cv.dto.portfolioDto.PortfolioResponseDto;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.entity.CvSkillStack;
import com.cv.domain.cv.entity.Link;
import com.cv.domain.cv.entity.Portfolio;
import com.cv.domain.education.mapper.EducationMapper;
import com.cv.domain.project.mapper.ProjectMapper;
import com.cv.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ProjectMapper.class, CareerMapper.class, CustomSectionMapper.class, EducationMapper.class
})
public interface CvMapper {
    @Mapping(source = "uuid", target = "user.uuid")
    @Mapping(target = "user", ignore = true)
    Cv cvPostToCv(CvPostDto post);
    Cv cvPatchToCv(CvPatchDto patch);
    @Mapping(source = "user.uuid", target = "uuid")
    CvResponseDto cvToCvResponse(Cv cv);
    @Mapping(source = "skillStackId", target = "skillStack.skillStackId")
    CvSkillStack cvSkillStackAddToCvSkillStack(CvSkillStackAddDto cvSkillStackAdd);
    @Mapping(source = "skillStack.skillStackId", target = "skillStackId")
    @Mapping(source = "skillStack.skillName", target = "skillName")
    CvSkillStackResponseDto cvSkillStackToCvSkillStackResponse(CvSkillStack cvSkillStack);
    List<CvSkillStack> cvSkillStackAddsToCvSkillStacks(List<CvSkillStackAddDto> cvSkillStackAdds);
    List<CvSkillStackResponseDto> cvSkillStacksToCvSkillStackResponses(List<CvSkillStack> skillStacks);
    Link linkAddToLink(LinkAddDto linkAdd);
    LinkResponseDto linkToLinkResponse(Link link);
    List<Link> linkAddsToLinks(List<LinkAddDto> linkAdds);
    List<LinkResponseDto> linksToLinkResponses(List<Link> links);
    Portfolio portfolioAddToPortfolio(PortfolioAddDto portfolioAdd);
    PortfolioResponseDto portfolioToPortfolioResponse(Portfolio portfolio);
    List<Portfolio> portfolioAddsToPortfolios(List<PortfolioAddDto> portfolioAdds);
    List<PortfolioResponseDto> portfolioToPortfolioResponses(List<Portfolio> portfolios);
}
