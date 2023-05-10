package com.cv.domain.cv.service;

import com.cv.domain.career.entity.Career;
import com.cv.domain.career.repository.CareerRepository;
import com.cv.domain.customSection.entity.CustomSection;
import com.cv.domain.customSection.repository.CustomSectionRepository;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.entity.CvSkillStack;
import com.cv.domain.cv.entity.Link;
import com.cv.domain.cv.repository.CvRepository;
import com.cv.domain.cv.repository.LinkRepository;
import com.cv.domain.education.entity.Education;
import com.cv.domain.education.repository.EducationRepository;
import com.cv.domain.project.entity.Project;
import com.cv.domain.project.repository.ProjectRepository;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CvService {

    private final CvRepository cvRepository;
    private final CustomSectionRepository customSectionRepository;
    private final LinkRepository linkRepository;
    private final ProjectRepository projectRepository;
    private final EducationRepository educationRepository;
    private final CareerRepository careerRepository;

    public Cv createCv(Cv cv){
        // TODO user 정보가 있는지 확인하는 로직 추가
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

    public void injectDownDomain(Cv cv) {
        List<Link> links = cv.getLinks();
        List<Link> newLinks = new ArrayList<>();
        for(Link l : links){
            l.setCv(cv);
            newLinks.add(linkRepository.save(l));
        }
        cv.setLinks(newLinks);

        List<Project> projects = cv.getProjects();
        List<Project> newProjects = new ArrayList<>();
        for(Project p : projects){
            p.setCv(cv);
            newProjects.add(projectRepository.save(p));
        }
        cv.setProjects(newProjects);

        List<CustomSection> customSections = cv.getCustomSections();
        List<CustomSection> newCustomSections = new ArrayList<>();
        for(CustomSection c : customSections){
            c.setCv(cv);
            newCustomSections.add(customSectionRepository.save(c));
        }
        cv.setCustomSections(newCustomSections);

        List<Education> educations = cv.getEducations();
        List<Education> newEducations = new ArrayList<>();
        for(Education e : educations){
            e.setCv(cv);
            newEducations.add(educationRepository.save(e));
        }
        cv.setEducations(newEducations);

        List<Career> careers = cv.getCareers();
        List<Career> newCareers = new ArrayList<>();
        for(Career c : careers){
            c.setCv(cv);
            newCareers.add(careerRepository.save(c));
        }
        cv.setCareers(newCareers);
    }
}
