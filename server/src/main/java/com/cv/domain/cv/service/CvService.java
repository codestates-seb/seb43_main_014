package com.cv.domain.cv.service;

import com.cv.domain.career.repository.CareerRepository;
import com.cv.domain.customSection.repository.CustomSectionRepository;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.entity.CvSkillStack;
import com.cv.domain.cv.repository.CvRepository;
import com.cv.domain.cv.repository.CvSkillStackRepository;
import com.cv.domain.cv.repository.LinkRepository;
import com.cv.domain.education.repository.EducationRepository;
import com.cv.domain.project.repository.ProjectRepository;
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
    private final CvSkillStackRepository cvSkillStackRepository;

    public Cv createCv(Cv cv){
        // TODO user 정보가 있는지 확인하는 로직 추가
        findExistSkillStack(cv);

        return cvRepository.save(cv);
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

        findCv.getCvSkillStacks().clear();
        findCv.getCvSkillStacks().addAll(cv.getCvSkillStacks());

        findCv.getLinks().clear();
        findCv.getLinks().addAll(cv.getLinks());

        findCv.getPortfolios().clear();
        findCv.getPortfolios().addAll(cv.getPortfolios());

        findCv.getEducations().clear();
        findCv.getEducations().addAll(cv.getEducations());

        findCv.getCareers().clear();
        findCv.getCareers().addAll(cv.getCareers());

        findCv.getProjects().clear();
        findCv.getProjects().addAll(cv.getProjects());

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
        for (CvSkillStack cvSkillStack : cv.getCvSkillStacks()) {
            SkillStack findSkillStack =skillStackRepository.findById(cvSkillStack.getSkillStack().getSkillStackId())
                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.SKILL_STACK_NOT_FOUND));

            cvSkillStack.setSkillStack(findSkillStack);
        }
    }
//
//    private void putInformationForSkillStack(Cv cv) {
//        List<CvSkillStack> cvSkillStackList = cv.getCvSkillStacks().stream()
//                .map(cvSkillStack -> {
//                    SkillStack skillStack;
//                    Optional<SkillStack> optionalSkillStack = skillStackRepository.findBySkillName((cvSkillStack.getSkillStack().getSkillName()));
//                    if (optionalSkillStack.isEmpty()) {
//                        throw new BusinessLogicException(ExceptionCode.SKILL_STACK_NOT_FOUND);
//                    } else {
//                        skillStack = optionalSkillStack.get();
//                        cvSkillStack.setSkillStack(skillStack);
//                    }
//                    skillStack.addCvSkillStack(cvSkillStack);
//                    return cvSkillStack;
//                })
//                .collect(Collectors.toList());
//
//        cvSkillStackList.stream()
//                .map(cvSkillStackRepository::save)
//                .collect(Collectors.toList());
//
//        cv.setCvSkillStacks(cvSkillStackList);
//    }
}
