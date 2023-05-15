package com.cv.helper;

import com.cv.domain.career.dto.CareerDto;
import com.cv.domain.career.dto.CareerSkillStackDto;
import com.cv.domain.customSection.dto.CustomSectionDto;
import com.cv.domain.cv.dto.CvDto;
import com.cv.domain.cv.dto.CvSkillStackDto;
import com.cv.domain.cv.dto.LinkDto;
import com.cv.domain.cv.entity.Link;
import com.cv.domain.education.dto.EducationDto;
import com.cv.domain.project.dto.ProjectDto;
import com.cv.domain.project.dto.ProjectSkillStackDto;
import com.cv.domain.skillStack.entity.SkillStack;

import java.util.ArrayList;
import java.util.List;

public class StubDataForCv {

    private static final SkillStack skillStack1 = new SkillStack();
    private static final SkillStack skillStack2 = new SkillStack();

    static {
        skillStack1.setSkillStackId(1L);
        skillStack1.setSkillName("Java");
        skillStack2.setSkillStackId(2L);
        skillStack2.setSkillName("JavaScript");
    }
    public static CvDto.Post getCvPost() {
        CvDto.Post post = new CvDto.Post();
        post.setUserId(1L);
        post.setName("홍길동");
        post.setEmail("hgd@gmail.com");
        post.setPhone("010-1234-5678");
        post.setAddress("주소");
        post.setBirthDay("2일");
        post.setBirthMonth("3월");
        post.setBirthYear("2003년");
        post.setSelfIntroduction("자기소개");
        post.setDevelopmentJob("개발 직무");
        post.setCvSkillStacks(getCvSkillStackAdds());
        post.setLinks(getLinkAdds());
        post.setEducations(getEducationAdds());
        post.setCareers(getCareerAdds());
        post.setProjects(getProjectAdds());
        post.setCustomSections(getCustomSectionAdds());

        return post;
    }

    public static CvDto.Patch getCvPatch(){
        CvDto.Patch patch = new CvDto.Patch();
        patch.setCvId(1L);
        patch.setName("유성영");
        patch.setEmail("tkfkdgowksel@gmail.com");
        patch.setPhone("010-0000-1111");
        patch.setAddress("변경된 주소");
        patch.setBirthDay("25일");
        patch.setBirthMonth("1월");
        patch.setBirthYear("1997년");
        patch.setSelfIntroduction("변경된 자기소개");
        patch.setDevelopmentJob("변경된 개발 직무");
        patch.setCvSkillStacks(getCvSkillStackAdds());
        patch.setLinks(getLinkAdds());
        patch.setEducations(getEducationAdds());
        patch.setCareers(getCareerAdds());
        patch.setProjects(getProjectAdds());
        patch.setCustomSections(getCustomSectionAdds());

        return patch;
    }

    public static CvDto.Response getCvPatchResponse(){
        CvDto.Response response = new CvDto.Response();
        response.setCvId(1L);
        response.setUserId(1L);
        response.setName("유성영");
        response.setEmail("tkfkdgowksel@gmail.com");
        response.setPhone("010-0000-1111");
        response.setAddress("변경된 주소");
        response.setBirthDay("25일");
        response.setBirthMonth("1월");
        response.setBirthYear("1997년");
        response.setSelfIntroduction("변경된 자기소개");
        response.setDevelopmentJob("변경된 개발 직무");
        response.setIsDelete(false);
        response.setCvSkillStacks(getCvSkillStackResponses());
        response.setLinks(getLinkResponses());
        response.setEducations(getEducationResponses());
        response.setCareers(getCareerResponses());
        response.setProjects(getProjectResponses());
        response.setCustomSections(getCustomSectionResponses());

        return response;
    }

    public static CvDto.Response getCvResponse() {
        CvDto.Response response = new CvDto.Response();
        response.setCvId(1L);
        response.setUserId(1L);
        response.setName("홍길동");
        response.setEmail("hgd@gmail.com");
        response.setPhone("010-1234-5678");
        response.setAddress("주소");
        response.setBirthDay("2일");
        response.setBirthMonth("3월");
        response.setBirthYear("2003년");
        response.setSelfIntroduction("자기소개");
        response.setDevelopmentJob("개발 직무");
        response.setIsDelete(false);
        response.setCvSkillStacks(getCvSkillStackResponses());
        response.setLinks(getLinkResponses());
        response.setEducations(getEducationResponses());
        response.setCareers(getCareerResponses());
        response.setProjects(getProjectResponses());
        response.setCustomSections(getCustomSectionResponses());

        return response;
    }

    public static List<EducationDto.Add> getEducationAdds() {
        EducationDto.Add education = new EducationDto.Add();
        education.setAdmissionYear("1999년");
        education.setAdmissionMonth("7월");
        education.setGraduationYear("2003년");
        education.setGraduationMonth("7월");
        education.setSchoolName("코딩대학교");
        education.setMajor("코딩학과");
        education.setDegree("학위");
        education.setDescription("교육 설명");

        List<EducationDto.Add> educations = new ArrayList<>();
        educations.add(education);

        return educations;
    }

    public static List<EducationDto.Response> getEducationResponses() {
        EducationDto.Response education = new EducationDto.Response();
        education.setEducationId(1L);
        education.setAdmissionYear("1999년");
        education.setAdmissionMonth("7월");
        education.setGraduationYear("2003년");
        education.setGraduationMonth("7월");
        education.setSchoolName("코딩대학교");
        education.setMajor("코딩학과");
        education.setDegree("학위");
        education.setDescription("교육 설명");

        List<EducationDto.Response> educations = new ArrayList<>();
        educations.add(education);

        return educations;
    }

    public static List<CvSkillStackDto.Add> getCvSkillStackAdds() {
        CvSkillStackDto.Add cvSkillStack1 = new CvSkillStackDto.Add();
        cvSkillStack1.setSkillStackId(1L);
        CvSkillStackDto.Add cvSkillStack2 = new CvSkillStackDto.Add();
        cvSkillStack2.setSkillStackId(2L);

        List<CvSkillStackDto.Add> cvSkillStackAdds = new ArrayList<>();
        cvSkillStackAdds.add(cvSkillStack1);
        cvSkillStackAdds.add(cvSkillStack2);

        return cvSkillStackAdds;
    }

    public static List<CvSkillStackDto.Response> getCvSkillStackResponses() {
        CvSkillStackDto.Response cvSkillStack1 = new CvSkillStackDto.Response();
        cvSkillStack1.setSkillStackId(1L);
        cvSkillStack1.setSkillName("Java");
        CvSkillStackDto.Response cvSkillStack2 = new CvSkillStackDto.Response();
        cvSkillStack2.setSkillStackId(2L);
        cvSkillStack2.setSkillName("JavaScript");

        List<CvSkillStackDto.Response> cvSkillStackAdds = new ArrayList<>();
        cvSkillStackAdds.add(cvSkillStack1);
        cvSkillStackAdds.add(cvSkillStack2);

        return cvSkillStackAdds;
    }

    public static List<CareerDto.Add> getCareerAdds() {
        CareerSkillStackDto.Add careerSkillStack1 = new CareerSkillStackDto.Add();
        careerSkillStack1.setSkillStackId(1L);
        CareerSkillStackDto.Add careerSkillStack2 = new CareerSkillStackDto.Add();
        careerSkillStack2.setSkillStackId(2L);

        List<CareerSkillStackDto.Add> careerSkillStacks = new ArrayList<>();
        careerSkillStacks.add(careerSkillStack1);
        careerSkillStacks.add(careerSkillStack2);

        CareerDto.Add career = new CareerDto.Add();
        career.setCompanyName("회사명");
        career.setDuty("직책");
        career.setDevelopmentJob("풀스택 개발자");
        career.setJoinMonth("9월");
        career.setJoinYear("2010년");
        career.setRetirementMonth("2월");
        career.setRetirementYear("2015년");
        career.setDescription("설명");
        career.setCareerSkillStacks(careerSkillStacks);

        List<CareerDto.Add> careers = new ArrayList<>();
        careers.add(career);

        return careers;
    }

    public static List<CareerDto.Response> getCareerResponses() {
        CareerSkillStackDto.Response careerSkillStack1 = new CareerSkillStackDto.Response();
        careerSkillStack1.setSkillStackId(1L);
        careerSkillStack1.setSkillName("Java");
        CareerSkillStackDto.Response careerSkillStack2 = new CareerSkillStackDto.Response();
        careerSkillStack2.setSkillStackId(2L);
        careerSkillStack2.setSkillName("JavaScript");

        List<CareerSkillStackDto.Response> careerSkillStacks = new ArrayList<>();
        careerSkillStacks.add(careerSkillStack1);
        careerSkillStacks.add(careerSkillStack2);

        CareerDto.Response career = new CareerDto.Response();
        career.setCareerId(1L);
        career.setCompanyName("회사명");
        career.setDuty("직책");
        career.setDevelopmentJob("풀스택 개발자");
        career.setJoinMonth("9월");
        career.setJoinYear("2010년");
        career.setRetirementMonth("2월");
        career.setRetirementYear("2015년");
        career.setDescription("설명");
        career.setCareerSkillStacks(careerSkillStacks);

        List<CareerDto.Response> careers = new ArrayList<>();
        careers.add(career);

        return careers;
    }

    public static List<CustomSectionDto.Add> getCustomSectionAdds() {
        CustomSectionDto.Add customSection = new CustomSectionDto.Add();
        customSection.setCustomName("사용자 정의 이름");
        customSection.setCustomContent("사용자 정의 내용");

        List<CustomSectionDto.Add> customSections = new ArrayList<>();
        customSections.add(customSection);

        return customSections;
    }

    public static List<CustomSectionDto.Response> getCustomSectionResponses() {
        CustomSectionDto.Response customSection = new CustomSectionDto.Response();
        customSection.setCustomSectionId(1L);
        customSection.setCustomName("사용자 정의 이름");
        customSection.setCustomContent("사용자 정의 내용");

        List<CustomSectionDto.Response> customSections = new ArrayList<>();
        customSections.add(customSection);

        return customSections;
    }

    public static List<ProjectDto.Add> getProjectAdds() {
        ProjectSkillStackDto.Add projectSkillStack1 = new ProjectSkillStackDto.Add();
        projectSkillStack1.setSkillStackId(1L);
        ProjectSkillStackDto.Add projectSkillStack2 = new ProjectSkillStackDto.Add();
        projectSkillStack2.setSkillStackId(2L);

        List<ProjectSkillStackDto.Add> projectSkillStacks = new ArrayList<>();
        projectSkillStacks.add(projectSkillStack1);
        projectSkillStacks.add(projectSkillStack2);

        ProjectDto.Add project = new ProjectDto.Add();
        project.setStartYear("1999년");
        project.setStartMonth("2월");
        project.setEndYear("2000년");
        project.setEndMonth("2월");
        project.setProjectSubject("프로젝트명");
        project.setProjectSkillStacks(projectSkillStacks);
        project.setDescription("프로젝트 내용");
        project.setLink("저장소 링크");

        List<ProjectDto.Add> projects = new ArrayList<>();
        projects.add(project);

        return projects;
    }

    public static List<ProjectDto.Response> getProjectResponses() {
        ProjectSkillStackDto.Response projectSkillStack1 = new ProjectSkillStackDto.Response();
        projectSkillStack1.setSkillStackId(1L);
        projectSkillStack1.setSkillName("Java");
        ProjectSkillStackDto.Response projectSkillStack2 = new ProjectSkillStackDto.Response();
        projectSkillStack2.setSkillStackId(2L);
        projectSkillStack2.setSkillName("JavaScript");

        List<ProjectSkillStackDto.Response> projectSkillStacks = new ArrayList<>();
        projectSkillStacks.add(projectSkillStack1);
        projectSkillStacks.add(projectSkillStack2);

        ProjectDto.Response project = new ProjectDto.Response();
        project.setProjectId(1L);
        project.setStartYear("1999년");
        project.setStartMonth("2월");
        project.setEndYear("2000년");
        project.setEndMonth("2월");
        project.setProjectSubject("프로젝트명");
        project.setProjectSkillStacks(projectSkillStacks);
        project.setDescription("프로젝트 내용");
        project.setLink("저장소 링크");

        List<ProjectDto.Response> projects = new ArrayList<>();
        projects.add(project);

        return projects;
    }

    public static List<LinkDto.Add> getLinkAdds() {
        LinkDto.Add link1 = new LinkDto.Add();
        link1.setLinkName(Link.LinkName.LINK_GITHUB);
        link1.setLinkAddress("깃허브 주소");
        LinkDto.Add link2 = new LinkDto.Add();
        link2.setLinkName(Link.LinkName.LINK_BLOG);
        link2.setLinkAddress("블로그 주소");

        List<LinkDto.Add> links = new ArrayList<>();
        links.add(link1);
        links.add(link2);

        return links;
    }

    public static List<LinkDto.Response> getLinkResponses() {
        LinkDto.Response link1 = new LinkDto.Response();
        link1.setLinkId(1L);
        link1.setLinkName(Link.LinkName.LINK_GITHUB);
        link1.setLinkAddress("깃허브 주소");
        LinkDto.Response link2 = new LinkDto.Response();
        link2.setLinkId(2L);
        link2.setLinkName(Link.LinkName.LINK_BLOG);
        link2.setLinkAddress("블로그 주소");

        List<LinkDto.Response> links = new ArrayList<>();
        links.add(link1);
        links.add(link2);

        return links;
    }
}
