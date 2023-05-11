package com.cv.domain.cv.service;

import com.cv.domain.career.entity.Career;
import com.cv.domain.career.entity.CareerSkillStack;
import com.cv.domain.career.repository.CareerRepository;
import com.cv.domain.career.repository.CareerSkillStackRepository;
import com.cv.domain.customSection.repository.CustomSectionRepository;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.entity.CvSkillStack;
import com.cv.domain.cv.repository.CvRepository;
import com.cv.domain.cv.repository.CvSkillStackRepository;
import com.cv.domain.cv.repository.LinkRepository;
import com.cv.domain.education.repository.EducationRepository;
import com.cv.domain.project.entity.Project;
import com.cv.domain.project.entity.ProjectSkillStack;
import com.cv.domain.project.repository.ProjectRepository;
import com.cv.domain.project.repository.ProjectSkillStackRepository;
import com.cv.domain.skillStack.entity.SkillStack;
import com.cv.domain.skillStack.repository.SkillStackRepository;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CvService {

    private final CvRepository cvRepository;
    private final SkillStackRepository skillStackRepository;
    private final CareerSkillStackRepository careerSkillStackRepository;
    private final CareerRepository careerRepository;
    private final ProjectSkillStackRepository projectSkillStackRepository;


    public Cv createCv(Cv cv){
        // TODO user 정보가 있는지 확인하는 로직 추가
        return cvRepository.save(cv);
    }
    public void injectLowDomain(Cv cv){

        findExistSkillStack(cv);
    }
    public Cv updateCv(Cv cv) {
        Cv findCv = findVerifiedCv(cv.getCvId());

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
//        if (!findCv.getCvSkillStacks().isEmpty()) {
//            findCv.getCvSkillStacks().clear();
//            findCv.setCvSkillStacks(cv.getCvSkillStacks());
//        } else {
//            cv.getCvSkillStacks().clear();
//        }
//
//        if (!findCv.getLinks().isEmpty()) {
//            findCv.getLinks().clear();
//            findCv.setLinks(cv.getLinks());
//        } else {
//            cv.getLinks().clear();
//        }
//
//        if (!findCv.getPortfolios().isEmpty()) {
//            findCv.getPortfolios().clear();
//            findCv.setPortfolios(cv.getPortfolios());
//        } else {
//            cv.getPortfolios().clear();
//        }
//
//        if (!findCv.getEducations().isEmpty()) {
//            findCv.getEducations().clear();
//            findCv.setEducations(cv.getEducations());
//        } else {
//            cv.getEducations().clear();
//        }
//
//        if (!findCv.getCareers().isEmpty()) {
//            findCv.getCareers().clear();
//            findCv.setCareers(cv.getCareers());
//        } else {
//            cv.getCareers().clear();
//        }
//
//        if (!findCv.getProjects().isEmpty()) {
//            findCv.getProjects().clear();
//            findCv.setProjects(cv.getProjects());
//        } else {
//            cv.getProjects().clear();
//        }

        return cvRepository.save(findCv);
    }

    public Cv getCv(long cvId) {

        return findVerifiedCv(cvId);
    }

    public void deleteCv(long cvId) {
        Cv findCv = findVerifiedCv(cvId);
        findCv.setIsDelete(true);
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
}
