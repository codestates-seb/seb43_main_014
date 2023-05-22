package com.cv.domain.cv.dto;

import com.cv.domain.customSection.dto.CustomSectionDto;
import com.cv.domain.education.dto.EducationDto;
import com.cv.domain.career.dto.CareerDto;
import com.cv.domain.project.dto.ProjectDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

public class CvDto {

    @Data
    public static class Post {
        private long userId;

        @Size(min = 1, message = "제목은 필수 작성 사항입니다.")
        private String title;

        private String name;

        private String email;

        private String phone;

        private String imageUrl;

        private String address;

        private String birthDay;

        private String birthMonth;

        private String birthYear;

        private String selfIntroduction;

        private String developmentJob;

        private List<CvSkillStackDto.Add> cvSkillStacks;

        private List<EducationDto.Add> educations;

        private List<CareerDto.Add> careers;

        private List<CustomSectionDto.Add> customSections;

        private List<ProjectDto.Add> projects;

        private List<LinkDto.Add> links;

        private List<PortfolioDto.Add> portfolios;
    }

    @Data
    public static class Patch {
        private long cvId;

        private String title;

        private String name;

        private String email;

        private String phone;

        private String imageUrl;

        private String address;

        private String birthDay;

        private String birthMonth;

        private String birthYear;

        private String selfIntroduction;

        private String developmentJob;

        private List<CvSkillStackDto.Add> cvSkillStacks;

        private List<EducationDto.Add> educations;

        private List<CareerDto.Add> careers;

        private List<CustomSectionDto.Add> customSections;

        private List<ProjectDto.Add> projects;

        private List<LinkDto.Add> links;

        private List<PortfolioDto.Add> portfolios;
    }

    @Data
    public static class Response {
        @Schema(description = "이력서 식별자", example = "1")
        private Long cvId;

        @Schema(description = "이력서 제목", example = "이력서 제목")
        private String title;

        @Schema(description = "회원 식별자", example = "1")
        private long userId;

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

        @Schema(description = "이력서가 삭제 상태인지 확인 true = 삭제상태", example = "false")
        private Boolean isDelete;

        @Schema(description = "이력서에 작성할 기술 스택")
        private List<CvSkillStackDto.Response> cvSkillStacks;

        @Schema(description = "이력서에 작성할 교육 사항")
        private List<EducationDto.Response> educations;

        @Schema(description = "이력서에 작성할 경력 사항")
        private List<CareerDto.Response> careers;

        @Schema(description = "사용자 정의  사항")
        private List<CustomSectionDto.Response> customSections;

        @Schema(description = "이력서에 작성할 프로젝트 경험")
        private List<ProjectDto.Response> projects;

        @Schema(description = "이력서에 첨부할 개인 링크")
        private List<LinkDto.Response> links;

        @Schema(description = "이력서에 첨부할 포트폴리오")
        private List<PortfolioDto.Response> portfolios;
    }

}
