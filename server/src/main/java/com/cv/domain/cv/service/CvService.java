package com.cv.domain.cv.service;

import com.cv.domain.cv.entity.Cv;

public interface CvService {
    Cv createCv(Cv cv);
    Cv updateCv(Cv cv);
    Cv getCv(long cvId);
    void deleteCv(long cvId);
    void isCvValid(Cv cv);
    Cv findVerifiedCv(long cvId);
}