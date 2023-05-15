package com.cv.domain.cv.service;

import com.cv.domain.career.entity.Career;
import com.cv.domain.career.entity.CareerSkillStack;
import com.cv.domain.career.repository.CareerRepository;
import com.cv.domain.career.repository.CareerSkillStackRepository;
import com.cv.domain.customSection.entity.CustomSection;
import com.cv.domain.customSection.repository.CustomSectionRepository;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.entity.CvSkillStack;
import com.cv.domain.cv.repository.CvRepository;
import com.cv.domain.cv.repository.CvSkillStackRepository;
import com.cv.domain.cv.repository.LinkRepository;
import com.cv.domain.education.entity.Education;
import com.cv.domain.education.repository.EducationRepository;
import com.cv.domain.project.entity.Project;
import com.cv.domain.project.entity.ProjectSkillStack;
import com.cv.domain.project.repository.ProjectRepository;
import com.cv.domain.project.repository.ProjectSkillStackRepository;
import com.cv.domain.skillStack.entity.SkillStack;
import com.cv.domain.skillStack.repository.SkillStackRepository;
import com.cv.domain.user.service.UserService;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CvService {

    private final CvRepository cvRepository;
    private final SkillStackRepository skillStackRepository;
    private final CareerSkillStackRepository careerSkillStackRepository;
    private final ProjectSkillStackRepository projectSkillStackRepository;
    private final UserService userService;
    private final EducationRepository educationRepository;
    private final ProjectRepository projectRepository;
    private final CustomSectionRepository customSectionRepository;
    private final CareerRepository careerRepository;



    public Cv createCv(Cv cv){

        userService.findUser(cv.getUser().getUserId());
        return cvRepository.save(cv);
    }
    public void injectLowDomain(Cv cv){
        findExistSkillStack(cv);
    }
    public Cv updateCv(Cv cv) {
        Cv findCv = findVerifiedCv(cv.getCvId());

        IsCvValid(findCv);  // 이력서 상태가 IsDelete 인지 확인

        Optional.ofNullable(cv.getName())
                .ifPresentOrElse(findCv::setName, () -> findCv.setName(null));
        Optional.ofNullable(cv.getEmail())
                .ifPresentOrElse(findCv::setEmail, () -> findCv.setEmail(null));
        Optional.ofNullable(cv.getPhone())
                .ifPresentOrElse(findCv::setPhone, () -> findCv.setPhone(null));
        Optional.ofNullable(cv.getAddress())
                .ifPresentOrElse(findCv::setAddress, () -> findCv.setAddress(null));
        Optional.ofNullable(cv.getBirthDay())
                .ifPresentOrElse(findCv::setBirthDay, () -> findCv.setBirthDay(null));
        Optional.ofNullable(cv.getBirthMonth())
                .ifPresentOrElse(findCv::setBirthMonth, () -> findCv.setBirthMonth(null));
        Optional.ofNullable(cv.getBirthYear())
                .ifPresentOrElse(findCv::setBirthYear, () -> findCv.setBirthYear(null));
        Optional.ofNullable(cv.getSelfIntroduction())
                .ifPresentOrElse(findCv::setSelfIntroduction, () -> findCv.setSelfIntroduction(null));
        Optional.ofNullable(cv.getDevelopmentJob())
                .ifPresentOrElse(findCv::setDevelopmentJob, () -> findCv.setDevelopmentJob(null));

        if (cv.getEducations() != null) {
            if(cv.getEducations().size() > findCv.getEducations().size()){
                for (int i = 0; i < cv.getEducations().size() - findCv.getEducations().size(); i++) {
                    Education education = new Education();
                    education.setCv(findCv);
                    educationRepository.save(education);
                    findCv.getEducations().add(education);
                }
            }

            for (int i = 0; i < cv.getEducations().size(); i++) {
                findCv.getEducations().get(i).setAdmissionYear(cv.getEducations().get(i).getAdmissionYear());
                findCv.getEducations().get(i).setAdmissionMonth(cv.getEducations().get(i).getAdmissionMonth());
                findCv.getEducations().get(i).setGraduationYear(cv.getEducations().get(i).getGraduationYear());
                findCv.getEducations().get(i).setGraduationMonth(cv.getEducations().get(i).getGraduationMonth());
                findCv.getEducations().get(i).setSchoolName(cv.getEducations().get(i).getSchoolName());
                findCv.getEducations().get(i).setMajor(cv.getEducations().get(i).getMajor());
                findCv.getEducations().get(i).setDegree(cv.getEducations().get(i).getDegree());
                findCv.getEducations().get(i).setDescription(cv.getEducations().get(i).getDescription());
            }

            if (cv.getEducations().size() < findCv.getEducations().size()) {
                for (int i = cv.getEducations().size(); i < findCv.getEducations().size(); i++) {
                    findCv.getEducations().remove(i);
                }
            }
        } else {
            findCv.setEducations(null);
        }

        if(cv.getCustomSections() != null){
            if(cv.getCustomSections().size() > findCv.getCustomSections().size()){
                for (int i = 0; i < cv.getCustomSections().size() - findCv.getCustomSections().size(); i++) {
                    CustomSection customSection = new CustomSection();
                    customSection.setCv(findCv);
                    customSectionRepository.save(customSection);
                    findCv.getCustomSections().add(customSection);
                }
            }

            for(int i = 0; i < cv.getCustomSections().size(); i++){
                findCv.getCustomSections().get(i).setCustomName(cv.getCustomSections().get(i).getCustomName());
                findCv.getCustomSections().get(i).setCustomContent(cv.getCustomSections().get(i).getCustomContent());
            }

            if(cv.getCustomSections().size() < findCv.getCustomSections().size()){
                for(int i = cv.getCustomSections().size(); i < findCv.getCustomSections().size(); i++){
                    findCv.getCustomSections().remove(i);
                }
            }
        }   else{
            findCv.setCustomSections(null);
        }

        if(cv.getCareers() != null){
            if(cv.getCareers().size() > findCv.getCareers().size()){
                for(int i = 0; i < cv.getCareers().size() - findCv.getCareers().size(); i++){
                    Career career = new Career();
                    career.setCv(findCv);
                    careerRepository.save(career);
                    findCv.getCareers().add(career);
                }
            }

            for (int i = 0; i < cv.getCareers().size(); i++){
                findCv.getCareers().get(i).setJoinMonth(cv.getCareers().get(i).getJoinMonth());
                findCv.getCareers().get(i).setJoinYear(cv.getCareers().get(i).getJoinYear());
                findCv.getCareers().get(i).setDescription(cv.getCareers().get(i).getDescription());
                findCv.getCareers().get(i).setDuty(cv.getCareers().get(i).getDuty());
                findCv.getCareers().get(i).setRetirementMonth(cv.getCareers().get(i).getRetirementMonth());
                findCv.getCareers().get(i).setRetirementYear(cv.getCareers().get(i).getRetirementYear());
                findCv.getCareers().get(i).setCompanyName(cv.getCareers().get(i).getCompanyName());
                findCv.getCareers().get(i).setDevelopmentJob(cv.getCareers().get(i).getDevelopmentJob());
                for(int j = 0; j < cv.getCareers().get(i).getCareerSkillStacks().size(); j++){
                    SkillStack findSkillStack = skillStackRepository.findById(cv.getCareers().get(i).getCareerSkillStacks().get(j).getSkillStack().getSkillStackId())
                            .orElseThrow(() -> new BusinessLogicException(ExceptionCode.SKILL_STACK_NOT_FOUND));
                    cv.getCareers().get(i).getCareerSkillStacks().get(j).setSkillStack(findSkillStack);
                }
                findCv.getCareers().get(i).setCareerSkillStacks(cv.getCareers().get(i).getCareerSkillStacks());
            }

            if(cv.getCareers().size() < findCv.getCareers().size()){
                for (int i = cv.getCareers().size(); i < findCv.getCareers().size(); i++){
                    findCv.getCareers().remove(i);
                }
            }
        }   else{
            findCv.setCareers(null);
        }

        if(cv.getProjects() != null){
            if(cv.getProjects().size() > findCv.getProjects().size()){
                for(int i = 0; i < cv.getProjects().size() - findCv.getProjects().size(); i++){
                    Project project = new Project();
                    project.setCv(findCv);
                    projectRepository.save(project);
                    findCv.getProjects().add(project);
                }
            }

            for(int i = 0; i < cv.getProjects().size(); i++){
                findCv.getProjects().get(i).setStartMonth(cv.getProjects().get(i).getStartMonth());
                findCv.getProjects().get(i).setStartYear(cv.getProjects().get(i).getStartYear());
                findCv.getProjects().get(i).setEndYear(cv.getProjects().get(i).getEndYear());
                findCv.getProjects().get(i).setEndMonth(cv.getProjects().get(i).getEndMonth());
                findCv.getProjects().get(i).setProjectSubject(cv.getProjects().get(i).getProjectSubject());
                findCv.getProjects().get(i).setDescription(cv.getProjects().get(i).getDescription());
                findCv.getProjects().get(i).setLink(cv.getProjects().get(i).getLink());
                for(int j = 0; j < cv.getProjects().get(i).getProjectSkillStacks().size(); j++){
                    SkillStack findSkillStack = skillStackRepository.findById(cv.getProjects().get(i).getProjectSkillStacks().get(j).getSkillStack().getSkillStackId())
                            .orElseThrow(() -> new BusinessLogicException(ExceptionCode.SKILL_STACK_NOT_FOUND));
                    cv.getProjects().get(i).getProjectSkillStacks().get(j).setSkillStack(findSkillStack);
                }
                findCv.getProjects().get(i).setProjectSkillStacks(cv.getProjects().get(i).getProjectSkillStacks());
            }

            if(cv.getProjects().size() < findCv.getProjects().size()){
                for(int i = cv.getProjects().size(); i < findCv.getProjects().size(); i++){
                    findCv.getProjects().remove(i);
                }
            }
        }   else {
            findCv.setProjects(null);
        }

        return cvRepository.save(findCv);
    }

    public Cv getCv(long cvId) {
        Cv cv = findVerifiedCv(cvId);

        IsCvValid(cv);

        return cv;
    }

    public void deleteCv(long cvId) {
        Cv findCv = findVerifiedCv(cvId);
        IsCvValid(findCv);
        findCv.setIsDelete(true);
        cvRepository.save(findCv);
    }

    private Cv findVerifiedCv(long cvId) {
        Optional<Cv> optionalCv = cvRepository.findById(cvId);

        return optionalCv.orElseThrow(() -> new BusinessLogicException(ExceptionCode.RESUME_NOT_FOUND));
    }

    private void findExistSkillStack(Cv cv) {
        if(cv.getCvSkillStacks() != null)
            for (CvSkillStack cvSkillStack : cv.getCvSkillStacks()) {
                SkillStack findSkillStack = skillStackRepository.findById(cvSkillStack.getSkillStack().getSkillStackId())
                        .orElseThrow(() -> new BusinessLogicException(ExceptionCode.SKILL_STACK_NOT_FOUND));

                cvSkillStack.setSkillStack(findSkillStack);
            }

        if(cv.getCareers() != null)
            for (Career career : cv.getCareers()) {
                for (CareerSkillStack careerSkillStack : career.getCareerSkillStacks()) {
                    SkillStack findSkillStack = skillStackRepository.findById(careerSkillStack.getSkillStack().getSkillStackId())
                            .orElseThrow(() -> new BusinessLogicException(ExceptionCode.SKILL_STACK_NOT_FOUND));

                    careerSkillStack.setSkillStack(findSkillStack);

                    careerSkillStack.setCareer(career);
                    careerSkillStackRepository.save(careerSkillStack);
                }
            }

        if(cv.getProjects() != null)
            for (Project project : cv.getProjects()) {
                for (ProjectSkillStack projectSkillStack : project.getProjectSkillStacks()) {
                    SkillStack findSkillStack = skillStackRepository.findById(projectSkillStack.getSkillStack().getSkillStackId())
                            .orElseThrow(() -> new BusinessLogicException(ExceptionCode.SKILL_STACK_NOT_FOUND));

                projectSkillStack.setSkillStack(findSkillStack);

                projectSkillStack.setProject(project);
                projectSkillStackRepository.save(projectSkillStack);
            }
        }
    }

    private void IsCvValid(Cv findCv) {
        if (findCv.getIsDelete()) {
            throw new BusinessLogicException(ExceptionCode.RESUME_WAS_DELETED);
        }
    }
}
