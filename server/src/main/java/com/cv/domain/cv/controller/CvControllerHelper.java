package com.cv.domain.cv.controller;

import com.cv.domain.cv.dto.cvDto.CvPatchDto;
import com.cv.domain.cv.dto.cvDto.CvPostDto;
import com.cv.domain.cv.dto.cvDto.CvResponseDto;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.mapper.CvMapper;
import com.cv.domain.cv.service.CvService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CvControllerHelper {

    private final CvMapper mapper;
    private final CvService cvService;

    @Transactional
    public CvResponseDto createCvWithTransaction(CvPostDto requestBody) {
        Cv postCv = mapper.cvPostToCv(requestBody);
        Cv cv = cvService.createCv(postCv);
        cvService.injectLowDomain(cv);
        return mapper.cvToCvResponse(cv);
    }

    @Transactional
    public CvResponseDto updateCvWithTransaction(CvPatchDto requestBody) {
        Cv patchCv = mapper.cvPatchToCv(requestBody);
        Cv cv = cvService.updateCv(patchCv);

        return mapper.cvToCvResponse(cv);
    }

    @Transactional(readOnly = true)
    public CvResponseDto getCvWithTransaction(long cvId) {
        Cv cv = cvService.getCv(cvId);

        return mapper.cvToCvResponse(cv);
    }

    @Transactional
    public void deleteCvWithTransaction(long cvId) {
        cvService.deleteCv(cvId);
    }
}
