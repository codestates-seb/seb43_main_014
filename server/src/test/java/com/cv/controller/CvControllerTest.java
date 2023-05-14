package com.cv.controller;

import com.cv.domain.career.dto.CareerDto;
import com.cv.domain.career.dto.CareerSkillStackDto;
import com.cv.domain.customSection.dto.CustomSectionDto;
import com.cv.domain.cv.controller.CvController;
import com.cv.domain.cv.dto.CvDto;
import com.cv.domain.cv.dto.CvSkillStackDto;
import com.cv.domain.cv.dto.LinkDto;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.entity.Link;
import com.cv.domain.cv.mapper.CvMapper;
import com.cv.domain.cv.service.CvService;
import com.cv.domain.education.dto.EducationDto;
import com.cv.domain.project.dto.ProjectDto;
import com.cv.domain.project.dto.ProjectSkillStackDto;
import com.cv.domain.skillStack.entity.SkillStack;
import com.cv.global.config.SecurityConfiguration;
import com.cv.helper.StubDataForCv;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CvController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfiguration.class)
})
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

    @Test
    @WithMockUser
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
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
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
                .andExpect(jsonPath("$.links[0].linkAddress").value(post.getLinks().get(0).getLinkAddress()));
    }
}
