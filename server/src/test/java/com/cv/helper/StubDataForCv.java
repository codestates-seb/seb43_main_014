package com.cv.helper;

import com.cv.domain.career.dto.CareerDto;
import com.cv.domain.career.dto.CareerSkillStackDto;
import com.cv.domain.customSection.dto.CustomSectionDto;
import com.cv.domain.cv.dto.CvDto;
import com.cv.domain.cv.dto.CvSkillStackDto;
import com.cv.domain.cv.dto.LinkDto;
import com.cv.domain.cv.dto.PortfolioDto;
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
        post.setTitle("제목");
        post.setEmail("hgd@gmail.com");
        post.setPhone("010-1234-5678");
        post.setAddress("주소");
        post.setBirthDay("2일");
        post.setImageUrl("data:image/jpeg;base64,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");
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
        post.setPortfolios(getPortfolioAdds());

        return post;
    }

    public static CvDto.Patch getCvPatch(){
        CvDto.Patch patch = new CvDto.Patch();
        patch.setCvId(1L);
        patch.setName("유성영");
        patch.setTitle("변경된 제목");
        patch.setEmail("tkfkdgowksel@gmail.com");
        patch.setPhone("010-0000-1111");
        patch.setImageUrl("data:image/jpeg;base64,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");
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
        patch.setPortfolios(getPortfolioAdds());

        return patch;
    }

    public static CvDto.Response getCvPatchResponse(){
        CvDto.Response response = new CvDto.Response();
        response.setCvId(1L);
        response.setUserId(1L);
        response.setName("유성영");
        response.setEmail("tkfkdgowksel@gmail.com");
        response.setImageUrl("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxAPDxAQEg8QEA8PEA8QEBAQEA8QFRAPFxUXFhYVFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGxAQGi8lHyUtLS83LSstLS0rLS0tLS0uLSstLy0tLS0tLS0tLS0tLS0tLS0tLS0tLS0rLSstKy0tLf/AABEIAN0A5AMBIgACEQEDEQH/xAAbAAEAAgMBAQAAAAAAAAAAAAAAAQUCBAYDB//EADwQAAIBAgMFBgQFAwEJAAAAAAABAgMRBBIhBTFBUWEGEyIycYFSkaGxByNCwdEzcuFiFFNjgoOSk6LS/8QAGQEBAQEBAQEAAAAAAAAAAAAAAAECBAUD/8QAIxEBAAICAgICAgMAAAAAAAAAAAECAxEEIRIxQVEiMhMUQv/aAAwDAQACEQMRAD8A+1AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEM8sRiY01dv0XFlXicXKenljyW9+pNvtjw2u3au0Ip2is1t74GVHHxk7PwvqU6+hJNuv8ArV1p0SBSYfFyh1jye/2LbD4iM1dP1XFF25MmG1HqACviAAAAAAAAAAAAAAAAAAAAAAAAAGri8bGnpvlwiv3DVazadQ2JzUVduyXMrMTtJvSGi+J/sjTrVpTd5O/KPBfyeZNu7FxojuzJyu772+L3hEAjqiNJJIAUMoyad07NcTEBJja0wmPzaS0fB8Gb5zhvYPGuPhk7x4Pl6liXFm4/zVaghO5JXEAAAAAAAAAAAAAAAAAAAAVuPxu+EX/dLl0QbpSbzqE43HWvGG/jLl6dSsa383vfFmSIMvTx44pHSLAkgPqAkAAAAAABGSMRcI3MHislk34H/wCv+C2TOeub2zsTltB7n5Xy6Fhx58P+oWgAK4QAAAAAAAAAAAAAAMZSSTb3LVhYau0cTlWVPxS+i5lSZVaueTk+P0jwRiZenhx+FUAAPsEEkMKIEEsAwQSAJIAAAASSYkhJhc4DEZ42fmjo+vJm0UWGrZJp8Nz9C8TLDzM+Pxt0kAFfAAAAAAAAAAAA0trVLQUfjdvbibpUbVleol8Mb/N/4JL7YK7u0wAR6gBcXCgYIAEBsBQkgkIAAACABIBIAudn1M1Nc14fkUyLHZEvOvR/sWHNya7ptZAArzQAAAAAAAAAACl2k/zZf2x/cuil2krVn1jF/Vkl08X92sau0cS6NKpVUXN04SlkW+VleyNoiSI9JznY7tXT2lSlJLu6tOVp02725Nc0dFc+Vdo9gqjinitm4jJX1c6UNVfjqtLPkzzwv4oYmh4MXg7yWjlC9Nv/AJWrE39tXrNe5jp9auYTqJJttJLe3wPmNT8V3JflYGpKXWd1f2Rp1aG29s+Gov8AZMLLemnTTj6eaQ8oY2+q4PGU60c1OcakbtXg1JXXA9ym7Ldn6ez6Cowbk2805y3ylztwRclaQzTltagqyw/ew79q/d31t6G60cJ287IVMRUjjMLLLiadm1e2e25p8yTOljXy7u4ufLMB+JVfDPusdhZ54+FzgssnbnF6P2LiP4pbPav+eny7pfyNwnTu3Iqe0O3qOBoutVem6MF5py5I4faH4pqaccLhakpvRSq2ST/ti3crdlbHqY/Exr7Tr5VdOFKXgUlvyrhFE8o+Gq0m3cQ+m9ntrrG4eFdQlCM72jLfoy0R4YaEYRjGKSjFJRS3KK3Huma7+WZ9pN3ZL/Ml/avuaRu7IXjl0jH7sPhn/SVsADTygAAAAAAAAAACr2xDxQl0cf3X7loa+Po56bS3rWPqiS+uG3jeJUhz238Q51FQTagoKdWztmTbUY35aNs6CLurnO7bpZK6m/LVjGF/9cb2XyZHucbU37aKsllilGK3JaJGE1mVmk1yaT+56ZWS6T5GtPX/ABa8cNG6cUqc4+WcIpOL/dHR7ExzrU3mt3lOThUtubXFdGmmUkIlj2ZhdVqn6Z1Eo9VFKLfzTJpy8qtfDa7JKjbXaLDYOdOFepkdZ2g2m16yfBFpTqqSTTTi1dNO6aI81mRYxqVVFNtpRWrbdkl1ZWbI7Q4XGTqQoVVUdF2nZO3qnxXUaGxtWlRdOcq0IThCLk88VKyWvE4lbHw0/wAyWGpwzaxpxVlGL3X6nabcoOph6kVq2k7c0ne30OfupJNapq6HjEu/hxGpmWrh8JSp+SlCHVRSfzNlyzKz1XFNXTM1Qb/zoHRlHei+MO3dfT32LXdGrGkv6VW6jHf3c0r2XRpPTodOjltn08+JpRWvdN1Zv4VlcYr1bf0OqI8vl6i/QWex4aTlzlZei0+9yts9Et7dl6l/hqShCMV+lJepYh5fKvqunoACvPAAAAAAAAAAAAAFJtHD93O9vBN/9s3w9zSxOHjVi4TipRlo0zpatNTi4yV01ZopMThpUnrrDhPl0l/JHoYM++p9qfZfZ5TqTputN0YKLy7pa3tHOtbaepcV+ymHa8GelL4ozf1Tun7kbMq93iGnbJWgsr/4kb6fK3yZfiDkcnLF9+TgcVsCaqqnWqpU5eVU04uqlvTlw9EW9KlGEVGKSjFJJLgi/wAdg4VoOElo7NNaOMluafBnP080XKnP+pDRv4o8JLo/vcTD7Y+TbLH5T6VXaTs5h9oUu7rRejvCcdJQfT+Dhn2A2nhvDhNo2p8IylUhZelmj6iLGdPpNYn2+Vz/AA+2nirLF7RUqfGMZVJ39rJHc9mezOH2dTyUYu8rZ6kneU2vsuhdEoaIrEekFXS2F3leUaNTu07TqrKpKF+MeUny3cSyqTUYyk90U2XOxcL3VGN/PP8AMqP/AFy1a9t3sahjLntirus9tWh2YwkVrSVSXGVXxtv1ZWbd7PU45XRfcqclGSik1rxinuZ1hTbaq3qUqfBXm+rtaKXXVv2EuXj58s5N7VeAwFOhHLBPV3lJ6ylLm3xNm9tW7JErV5Us0uUf35e5ZYLZ1mpVLOS1jFbo/wAvqR0Zc0V7me0bMwjT7ySs2rQi/wBMXxfVlkAaebe82ncgADAAAAAAAAAAAAAAENXJAFVjtmx0cc0W5K0U9M3NcvY3sNOXkn5kt/CS5omt56frL7MznG/qtUH2teZiIlmVG3qFlGst9N2n1pt6/J2fsy2TueeKp54Ti90otfQM47zW0S564RzTwVGMczhlSjduMqkdy18r9Ta2TSw0ZxqTlJ0KkbJutVcU98ZK8uO75H3nj2ivk6q87HNpr2vMrMbkVamzFFvOpNLSOereT4KN3qc1VwuHg4xqRTnVm8sJSqVHd65Yq/lS47tDFMNrLfm46RuXQznGUoUrq9SpBWur2Tu9PRHVo4fY2CpU8RRcadODztXjGMX5ZcTuSXx+E6c+XPGWItEMak1FNvck2akcJGrSaqRzd48zT4N6q3K2hs16eZZeDav6HoYfOLajpo7MoxpqVOP6H72equbx4UfPUfWK+SPcF53OwABgAAAAAAAAAAAAAAAAAAHhitykt8Hm9uP0ue0Wmk1qmrok1VGVPyrPTeuVeaL6c0G/cNo8601GMm9yTZrz2lTWjU7/AA93O/2PNuVXxTXd0Y+LLK2aVtbytuXGwWKT7lQ7U2ZKjSVZNSiknKO6Sb+D4t+7f9igo0YRqwcIuMZpzt4ks2uuV7mb+1NoTqZq7hKdv6FGP6Yt2jpuzO93J7l6Gph6FZ2c5xdackoQivDF/BF75dW+R6WLdafk48ur3/H22qqzRabbVmaOFhkj3kaTnVqWjKTldxjfi275VyReYrYWK7uTXdbt0HOUmuOW6SuUtTDTXjoVLPc6c23Tm1wfGEuq90ajJW3pj+O2ON27dfsvZ9J0s9Oaq1Hb8zlJO+VL9K6b+ZcUaqmrrfua4p8UcVgMXKjUjVWl7KtFO6nDj6uO9PpY6+thszzwm6c/ijrddU9GcGak1t27KWi9W0edesoK/F6RXFvkjU7rE7u9pW5927/cYeg41fFOVSThfNK2mvBLRHybikfbaw1Nxjr5nrL1e89QAxM7AAEAAAAAAAAAAAAAAAAACAFyMwaMGiLEPOo/zKfrJfS/7Hpi6KqU5wbspxcb8rqxr4hOya3xalbmbdOopJNO6ZYbn1Eudj2dqX1qU0ukJSdvd2LXZ+yaVB5leVRqzqTd5W5LhFdEb4NzktPuWN/QV+M2NQqvM4uM+M6cnBv1tv8AcsAYidG9Kin2eop3cqs1ylPR+trXLZIkFmZn2bDVqO1Vf2fue9SpGKvJpLqatK8pSm00nZRT+Hn7mZapHy2VIlSMVEzUQzImSLElQAAAAAAAAAAAAAAAAAAAixIAwlAqsbhsTFuVDu03wlOSTfVWZcAmm638VJh8VtBaVMLSl1hX/wDqKN+niKz30Lf9SP8ABuAq2vE/DWdar/ul/wCRGLq1uFGHvV/iJtgJ5R9KfEVsfrkoYZcnOtU+ygakcPtSb8VXC01yhGpL6ux0YJ23GXXqIVeG2fUTvOcJS55ZN/NssYQt1MwNMWvNvYACsAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA/9k=");
        response.setTitle("변경된 제목");
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
        response.setPortfolios(getPortfolioResponses());

        return response;
    }

    public static CvDto.Response getCvResponse() {
        CvDto.Response response = new CvDto.Response();
        response.setCvId(1L);
        response.setUserId(1L);
        response.setName("홍길동");
        response.setTitle("제목");
        response.setImageUrl("data:image/jpeg;base64,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");
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
        response.setPortfolios(getPortfolioResponses());

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
        project.setPart("주임");
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
        project.setPart("주임");
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

    public static List<PortfolioDto.Add> getPortfolioAdds() {
        PortfolioDto.Add portfolio1 = new PortfolioDto.Add();
        portfolio1.setPortfolioAddress("포트폴리오 주소");

        List<PortfolioDto.Add> portfolios = new ArrayList<>();
        portfolios.add(portfolio1);

        return portfolios;
    }

    public static List<PortfolioDto.Response> getPortfolioResponses() {
        PortfolioDto.Response portfolio1 = new PortfolioDto.Response();
        portfolio1.setPortfolioId(1L);
        portfolio1.setPortfolioAddress("포트폴리오 주소");

        List<PortfolioDto.Response> portfolios = new ArrayList<>();
        portfolios.add(portfolio1);

        return portfolios;
    }
}
