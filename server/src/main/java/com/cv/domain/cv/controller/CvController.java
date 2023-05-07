package com.cv.domain.cv.controller;

import com.cv.domain.cv.mapper.CvMapper;
import com.cv.domain.cv.service.CvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RequestMapping("/cv")
@RestController
public class CvController {

    private CvMapper mapper;
    private CvService cvService;

    // 이력서 작성
    @PostMapping
    public ResponseEntity postCv() {
        return null;
    }

    // 이력서 수정
    @PatchMapping("/edit/{cv-id}")
    public ResponseEntity patchCv(@PathVariable("cv-id") long cvId) {
        return null;
    }

    // 이력서 조회
    @GetMapping("/{cv-id}")
    public ResponseEntity getCv(@PathVariable("cv-id") long cvId) {
        return null;
    }

    // 이력서 삭제
    @DeleteMapping("/{cv-id}")
    public ResponseEntity deleteCv(@PathVariable("cv-id") long cvId) {
        return null;
    }
}
