package com.cv.domain.cv.service;

import com.cv.domain.cv.entity.Cv;
import org.springframework.data.domain.Page;

public interface CvService {
    Cv createCv(Cv cv);
    Cv updateCv(Cv cv);
    Cv getCv(long cvId);
    void deleteCv(long cvId);
    void isCvValid(Cv cv);
    Cv findVerifiedCv(long cvId);
    Page<Cv> findLatestCvsByUser(String uuid, int page);
}