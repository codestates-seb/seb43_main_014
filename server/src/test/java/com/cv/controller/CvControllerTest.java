package com.cv.controller;

import com.cv.domain.cv.controller.CvController;
import com.cv.domain.cv.dto.CvDto;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.mapper.CvMapper;
import com.cv.domain.cv.service.CvService;
import com.cv.helper.StubDataForCv;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.cv.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.cv.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(value = CvController.class, excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebMvcConfigurer.class)
//})
@WebMvcTest(value = CvController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class CvControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CvService cvService;
    @MockBean
    private CvMapper mapper;
    @Autowired
    private Gson gson;

    @WithMockUser
    @Test
    public void postCvTest() throws Exception {

        // given
        CvDto.Post post = StubDataForCv.getCvPost();
        CvDto.Response response = StubDataForCv.getCvResponse();
        String content = gson.toJson(post);

        given(mapper.cvPostToCv(Mockito.any(CvDto.Post.class)))
                .willReturn(new Cv());

        given(cvService.createCv(Mockito.any(Cv.class)))
                .willReturn(new Cv());

        given(mapper.cvToCvResponse(Mockito.any(Cv.class)))
                .willReturn(response);

        // when
        ResultActions postActions =
                mockMvc.perform(
                        post("/cv")
                        .with(csrf())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                );

        // then
        postActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(post.getUserId()))
                .andExpect(jsonPath("$.name").value(post.getName()))
                .andExpect(jsonPath("$.email").value(post.getEmail()))
                .andExpect(jsonPath("$.phone").value(post.getPhone()))
                .andExpect(jsonPath("$.address").value(post.getAddress()))
                .andExpect(jsonPath("$.birthDay").value(post.getBirthDay()))
                .andExpect(jsonPath("$.birthMonth").value(post.getBirthMonth()))
                .andExpect(jsonPath("$.birthYear").value(post.getBirthYear()))
                .andExpect(jsonPath("$.selfIntroduction").value(post.getSelfIntroduction()))
                .andExpect(jsonPath("$.developmentJob").value(post.getDevelopmentJob()))
                .andExpect(jsonPath("$.cvSkillStacks[0].skillStackId").value(post.getCvSkillStacks().get(0).getSkillStackId()))
                .andExpect(jsonPath("$.cvSkillStacks[1].skillStackId").value(post.getCvSkillStacks().get(1).getSkillStackId()))
                .andExpect(jsonPath("$.educations[0].description").value(post.getEducations().get(0).getDescription()))
                .andExpect(jsonPath("$.careers[0].description").value(post.getCareers().get(0).getDescription()))
                .andExpect(jsonPath("$.customSections[0].customContent").value(post.getCustomSections().get(0).getCustomContent()))
                .andExpect(jsonPath("$.projects[0].description").value(post.getProjects().get(0).getDescription()))
                .andExpect(jsonPath("$.links[0].linkAddress").value(post.getLinks().get(0).getLinkAddress()))
                .andDo(document("post-cv",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("회원 식별자").optional(),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이력서에 작성할 이름").optional(),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이력서에 작성할 이메일").optional(),
                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("이력서에 작성할 연락처").optional(),
                                        fieldWithPath("address").type(JsonFieldType.STRING).description("이력서에 작성할 주소").optional(),
                                        fieldWithPath("birthDay").type(JsonFieldType.STRING).description("이력서에 작성할 태어난 일").optional(),
                                        fieldWithPath("birthMonth").type(JsonFieldType.STRING).description("이력서에 작성할 태어난 월").optional(),
                                        fieldWithPath("birthYear").type(JsonFieldType.STRING).description("이력서에 작성할 태어난 연도").optional(),
                                        fieldWithPath("selfIntroduction").type(JsonFieldType.STRING).description("이력서에 작성할 자기소개").optional(),
                                        fieldWithPath("developmentJob").type(JsonFieldType.STRING).description("이력서에 작성할 개발 직무").optional(),
                                        fieldWithPath("isDelete").type(JsonFieldType.BOOLEAN).description("이력서 상태 정보").optional(),
//                                        fieldWithPath("cvSkillStacks").type(JsonFieldType.ARRAY).description("이력서에 작성할 기술 스택 리스트").optional(),
                                        fieldWithPath("cvSkillStacks[].skillStackId").type(JsonFieldType.NUMBER).description("기술 스택 식별자").optional(),
                                        fieldWithPath("cvSkillStacks[].skillName").type(JsonFieldType.STRING).description("기술 스택").optional(),
//                                        fieldWithPath("educations").type(JsonFieldType.ARRAY).description("이력서에 작성할 교육 사항 리스트").optional(),
                                        fieldWithPath("educations[].educationId").type(JsonFieldType.NUMBER).description("교육 사항 식별자").optional(),
                                        fieldWithPath("educations[].degree").type(JsonFieldType.STRING).description("학위").optional(),
                                        fieldWithPath("educations[].major").type(JsonFieldType.STRING).description("전공").optional(),
                                        fieldWithPath("educations[].schoolName").type(JsonFieldType.STRING).description("학교명").optional(),
                                        fieldWithPath("educations[].admissionMonth").type(JsonFieldType.STRING).description("입학 월").optional(),
                                        fieldWithPath("educations[].admissionYear").type(JsonFieldType.STRING).description("입학 연도").optional(),
                                        fieldWithPath("educations[].graduationMonth").type(JsonFieldType.STRING).description("졸업 월").optional(),
                                        fieldWithPath("educations[].graduationYear").type(JsonFieldType.STRING).description("졸업 연도").optional(),
                                        fieldWithPath("educations[].description").type(JsonFieldType.STRING).description("설명").optional(),
//                                        fieldWithPath("careers").type(JsonFieldType.ARRAY).description("이력서에 작성할 경력 사항 리스트").optional(),
                                        fieldWithPath("careers[].joinMonth").type(JsonFieldType.STRING).description("입사 월").optional(),
                                        fieldWithPath("careers[].joinYear").type(JsonFieldType.STRING).description("입사 연도").optional(),
                                        fieldWithPath("careers[].retirementMonth").type(JsonFieldType.STRING).description("퇴사 월").optional(),
                                        fieldWithPath("careers[].retirementYear").type(JsonFieldType.STRING).description("퇴사 연도").optional(),
                                        fieldWithPath("careers[].companyName").type(JsonFieldType.STRING).description("회사 이름").optional(),
                                        fieldWithPath("careers[].duty").type(JsonFieldType.STRING).description("직책").optional(),
                                        fieldWithPath("careers[].developmentJob").type(JsonFieldType.STRING).description("개발 직무").optional(),
                                        fieldWithPath("careers[].description").type(JsonFieldType.STRING).description("설명").optional(),
                                        fieldWithPath("careers[].careerSkillStacks[].skillStackId").type(JsonFieldType.NUMBER).description("경력 사항에 작성할 기술 스택").optional(),
                                        fieldWithPath("careers[].careerSkillStacks[].skillName").type(JsonFieldType.STRING).description("경력 사항에 작성할 기술 스택").optional(),
//                                        fieldWithPath("customSections").type(JsonFieldType.ARRAY).description("이력서에 작성할 사용자 정의 사항 리스트").optional(),
                                        fieldWithPath("customSections[].customName").type(JsonFieldType.STRING).description("사용자 정의 이름").optional(),
                                        fieldWithPath("customSections[].customContent").type(JsonFieldType.STRING).description("사용자 정의 내용").optional(),
//                                        fieldWithPath("projects").type(JsonFieldType.ARRAY).description("이력서에 작성할 프로젝트 리스트").optional(),
                                        fieldWithPath("projects[].startMonth").type(JsonFieldType.STRING).description("시작 월").optional(),
                                        fieldWithPath("projects[].startYear").type(JsonFieldType.STRING).description("시작 연도").optional(),
                                        fieldWithPath("projects[].endMonth").type(JsonFieldType.STRING).description("종료 월").optional(),
                                        fieldWithPath("projects[].endYear").type(JsonFieldType.STRING).description("종료 연도").optional(),
                                        fieldWithPath("projects[].projectSubject").type(JsonFieldType.STRING).description("프로젝트 제목").optional(),
                                        fieldWithPath("projects[].description").type(JsonFieldType.STRING).description("설명").optional(),
                                        fieldWithPath("projects[].link").type(JsonFieldType.STRING).description("프로젝트 링크").optional(),
                                        fieldWithPath("projects[].projectSkillStacks[].skillStackId").type(JsonFieldType.NUMBER).description("프로젝트에 사용한 기술 스택 식별자").optional(),
                                        fieldWithPath("projects[].projectSkillStacks[].skillName").type(JsonFieldType.STRING).description("프로젝트에 사용한 기술 스택").optional(),
//                                        fieldWithPath("links").type(JsonFieldType.ARRAY).description("이력서에 작성할 개인 사이트(깃허브, 블로그 등) 리스트").optional()
                                        fieldWithPath("links[].linkName").type(JsonFieldType.STRING).description("링크 출처").optional(),
                                        fieldWithPath("links[].linkAddress").type(JsonFieldType.STRING).description("링크 주소").optional()
                                        )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("cvId").type(JsonFieldType.NUMBER).description("이력서 식별자").ignored(),
                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이력서에 작성할 이름"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이력서에 작성할 이메일"),
                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("이력서에 작성할 연락처"),
                                        fieldWithPath("address").type(JsonFieldType.STRING).description("이력서에 작성할 주소"),
                                        fieldWithPath("birthDay").type(JsonFieldType.STRING).description("이력서에 작성할 태어난 일"),
                                        fieldWithPath("birthMonth").type(JsonFieldType.STRING).description("이력서에 작성할 태어난 월"),
                                        fieldWithPath("birthYear").type(JsonFieldType.STRING).description("이력서에 작성할 태어난 연도"),
                                        fieldWithPath("selfIntroduction").type(JsonFieldType.STRING).description("이력서에 작성할 자기소개"),
                                        fieldWithPath("developmentJob").type(JsonFieldType.STRING).description("이력서에 작성할 개발 직무"),
                                        fieldWithPath("isDelete").type(JsonFieldType.BOOLEAN).description("이력서 상태 정보").optional(),
//                                        fieldWithPath("cvSkillStacks").type(JsonFieldType.ARRAY).description("이력서에 작성할 기술 스택 리스트"),
                                        fieldWithPath("cvSkillStacks[].skillStackId").type(JsonFieldType.NUMBER).description("기술 스택 식별자").optional(),
                                        fieldWithPath("cvSkillStacks[].skillName").type(JsonFieldType.STRING).description("기술 스택").optional(),
//                                        fieldWithPath("educations").type(JsonFieldType.ARRAY).description("이력서에 작성할 교육 사항 리스트"),
                                        fieldWithPath("educations[].educationId").type(JsonFieldType.NUMBER).description("교육 사항 식별자").optional(),
                                        fieldWithPath("educations[].degree").type(JsonFieldType.STRING).description("학위").optional(),
                                        fieldWithPath("educations[].major").type(JsonFieldType.STRING).description("전공").optional(),
                                        fieldWithPath("educations[].schoolName").type(JsonFieldType.STRING).description("학교명").optional(),
                                        fieldWithPath("educations[].admissionMonth").type(JsonFieldType.STRING).description("입학 월").optional(),
                                        fieldWithPath("educations[].admissionYear").type(JsonFieldType.STRING).description("입학 연도").optional(),
                                        fieldWithPath("educations[].graduationMonth").type(JsonFieldType.STRING).description("졸업 월").optional(),
                                        fieldWithPath("educations[].graduationYear").type(JsonFieldType.STRING).description("졸업 연도").optional(),
                                        fieldWithPath("educations[].description").type(JsonFieldType.STRING).description("설명").optional(),
//                                        fieldWithPath("careers").type(JsonFieldType.ARRAY).description("이력서에 작성할 경력 사항 리스트"),
                                        fieldWithPath("careers[].careerId").type(JsonFieldType.NUMBER).description("교육 사항 식별자").optional(),
                                        fieldWithPath("careers[].joinMonth").type(JsonFieldType.STRING).description("입사 월").optional(),
                                        fieldWithPath("careers[].joinYear").type(JsonFieldType.STRING).description("입사 연도").optional(),
                                        fieldWithPath("careers[].retirementMonth").type(JsonFieldType.STRING).description("퇴사 월").optional(),
                                        fieldWithPath("careers[].retirementYear").type(JsonFieldType.STRING).description("퇴사 연도").optional(),
                                        fieldWithPath("careers[].companyName").type(JsonFieldType.STRING).description("회사 이름").optional(),
                                        fieldWithPath("careers[].duty").type(JsonFieldType.STRING).description("직책").optional(),
                                        fieldWithPath("careers[].developmentJob").type(JsonFieldType.STRING).description("개발 직무").optional(),
                                        fieldWithPath("careers[].description").type(JsonFieldType.STRING).description("설명").optional(),
                                        fieldWithPath("careers[].careerSkillStacks[].skillStackId").type(JsonFieldType.NUMBER).description("경력 사항에 작성할 기술 스택 식별자").optional(),
                                        fieldWithPath("careers[].careerSkillStacks[].skillName").type(JsonFieldType.STRING).description("경력 사항에 작성할 기술 스택").optional(),
//                                        fieldWithPath("customSections").type(JsonFieldType.ARRAY).description("이력서에 작성할 사용자 정의 사항 리스트"),
                                        fieldWithPath("customSections[].customSectionId").type(JsonFieldType.NUMBER).description("사용자 정의 사항 식별자").optional(),
                                        fieldWithPath("customSections[].customName").type(JsonFieldType.STRING).description("사용자 정의 이름").optional(),
                                        fieldWithPath("customSections[].customContent").type(JsonFieldType.STRING).description("사용자 정의 내용").optional(),
//                                        fieldWithPath("projects").type(JsonFieldType.ARRAY).description("이력서에 작성할 프로젝트 리스트"),
                                        fieldWithPath("projects[].projectId").type(JsonFieldType.NUMBER).description("프로젝트 식별자").optional(),
                                        fieldWithPath("projects[].startMonth").type(JsonFieldType.STRING).description("시작 월").optional(),
                                        fieldWithPath("projects[].startYear").type(JsonFieldType.STRING).description("시작 연도").optional(),
                                        fieldWithPath("projects[].endMonth").type(JsonFieldType.STRING).description("종료 월").optional(),
                                        fieldWithPath("projects[].endYear").type(JsonFieldType.STRING).description("종료 연도").optional(),
                                        fieldWithPath("projects[].projectSubject").type(JsonFieldType.STRING).description("프로젝트 제목").optional(),
                                        fieldWithPath("projects[].description").type(JsonFieldType.STRING).description("설명").optional(),
                                        fieldWithPath("projects[].link").type(JsonFieldType.STRING).description("프로젝트 링크").optional(),
                                        fieldWithPath("projects[].projectSkillStacks[].skillStackId").type(JsonFieldType.NUMBER).description("프로젝트에 사용한 기술 스택 식별자").optional(),
                                        fieldWithPath("projects[].projectSkillStacks[].skillName").type(JsonFieldType.STRING).description("프로젝트에 사용한 기술 스택").optional(),
//                                        fieldWithPath("links").type(JsonFieldType.ARRAY).description("이력서에 작성할 개인 사이트(깃허브, 블로그 등) 리스트")
                                        fieldWithPath("links[].linkId").type(JsonFieldType.NUMBER).description("링크 식별자").optional(),
                                        fieldWithPath("links[].linkName").type(JsonFieldType.STRING).description("링크 출처").optional(),
                                        fieldWithPath("links[].linkAddress").type(JsonFieldType.STRING).description("링크 주소").optional()
                                        )
                        )
                ));
    }
}
