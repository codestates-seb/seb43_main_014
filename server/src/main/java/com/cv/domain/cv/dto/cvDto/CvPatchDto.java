package com.cv.domain.cv.dto.cvDto;

import com.cv.domain.career.dto.careerDto.CareerAddDto;
import com.cv.domain.career.dto.careerDto.CareerResponseDto;
import com.cv.domain.customSection.dto.CustomSectionAddDto;
import com.cv.domain.customSection.dto.CustomSectionResponseDto;
import com.cv.domain.cv.dto.linkDto.LinkAddDto;
import com.cv.domain.cv.dto.linkDto.LinkResponseDto;
import com.cv.domain.cv.dto.portfolioDto.PortfolioAddDto;
import com.cv.domain.cv.dto.portfolioDto.PortfolioResponseDto;
import com.cv.domain.education.dto.EducationAddDto;
import com.cv.domain.education.dto.EducationResponseDto;
import com.cv.domain.project.dto.projectDto.ProjectAddDto;
import com.cv.domain.project.dto.projectDto.ProjectResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class CvPatchDto {
    @Schema(description = "이력서 식별자", example = "1")
    private long cvId;

    @Schema(description = "이력서 제목", example = "이력서 제목")
    private String title;

    @Schema(description = "이력서에 작성할 이름", example = "홍길동")
    private String name;

    @Schema(description = "이력서에 작성할 이메일", example = "hgd@gmail.com")
    private String email;

    @Schema(description = "이미지 url", example = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAMA")
    private String imageUrl;

    @Schema(description = "이력서에 작성할 연락처", example = "010-1234-5678")
    private String phone;

    @Schema(description = "이력서에 작성할 주소", example = "부산 대마도")
    private String address;

    @Schema(description = "태어난 일", example = "15")
    private String birthDay;

    @Schema(description = "태어난 월", example = "1")
    private String birthMonth;

    @Schema(description = "태어난 연도", example = "1987")
    private String birthYear;

    @Schema(description = "자기소개", example = "안녕하세요 홍길동입니다")
    private String selfIntroduction;

    @Schema(description = "개발 직무", example = "풀스택")
    private String developmentJob;

    @Schema(description = "이력서에 작성할 기술 스택")
    private List<CvSkillStackAddDto> cvSkillStacks;

    @Schema(description = "이력서에 작성할 교육 사항")
    private List<EducationAddDto> educations;

    @Schema(description = "이력서에 작성할 경력 사항")
    private List<CareerAddDto> careers;

    @Schema(description = "사용자 정의  사항")
    private List<CustomSectionAddDto> customSections;

    @Schema(description = "이력서에 작성할 프로젝트 경험")
    private List<ProjectAddDto> projects;

    @Schema(description = "이력서에 첨부할 개인 링크")
    private List<LinkAddDto> links;

    @Schema(description = "이력서에 첨부할 포트폴리오")
    private List<PortfolioAddDto> portfolios;
}
