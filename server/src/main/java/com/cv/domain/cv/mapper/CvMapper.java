package com.cv.domain.cv.mapper;

import com.cv.domain.career.mapper.CareerMapper;
import com.cv.domain.customSection.mapper.CustomSectionMapper;
import com.cv.domain.cv.dto.CvDto;
import com.cv.domain.cv.dto.CvSkillStackDto;
import com.cv.domain.cv.dto.LinkDto;
import com.cv.domain.cv.dto.PortfolioDto;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.entity.CvSkillStack;
import com.cv.domain.cv.entity.Link;
import com.cv.domain.cv.entity.Portfolio;
import com.cv.domain.project.mapper.ProjectMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ProjectMapper.class, CareerMapper.class, CustomSectionMapper.class
})
public interface CvMapper {
    Cv cvPostToCv(CvDto.Post post);
    Cv cvPatchToCv(CvDto.Patch patch);
    CvDto.Response cvToCvResponse(Cv cv);

    CvSkillStack cvSkillStackAddToCvSkillStack(CvSkillStackDto.Add cvSkillStackAdd);
    CvSkillStackDto.Response cvSkillStackToCvSkillStackResponse(CvSkillStack cvSkillStack);
    List<CvSkillStack> cvSkillStackAddsToCvSkillStacks(List<CvSkillStackDto.Add> cvSkillStackAdds);
    List<CvSkillStackDto.Response> cvSkillStacksToCvSkillStackResponses(List<CvSkillStack> skillStacks);
    Link linkAddToLink(LinkDto.Add linkAdd);
    LinkDto.Response linkToLinkResponse(Link link);
    List<Link> linkAddsToLinks(List<LinkDto.Add> linkAdds);
    List<LinkDto.Response> linksToLinkResponses(List<Link> links);
    Portfolio portfolioAddToPortfolio(PortfolioDto.Add portfolioAdd);
    PortfolioDto.Response portfolioToPortfolioResponse(Portfolio portfolio);
    List<Portfolio> portfolioAddsToPortfolios(List<PortfolioDto.Add> portfolioAdds);
    List<PortfolioDto.Response> portfolioToPortfolioResponses(List<Portfolio> portfolios);
}
