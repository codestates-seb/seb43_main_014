package com.cv.domain.cv.controller;

import com.cv.domain.cv.dto.CvDto;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.mapper.CvMapper;
import com.cv.domain.cv.service.CvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Validated
@RequiredArgsConstructor
@RequestMapping("/cv")
@RestController
public class CvController {

    private CvMapper mapper;
    private CvService cvService;

    // 이력서 작성
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CvDto.Response> postCv(@RequestBody @Valid CvDto.Post requestBody,
                                                 @RequestParam(value="image")MultipartFile image) throws IOException {
        Cv postCv = mapper.cvPostToCv(requestBody);
        Cv cv = cvService.createCv(postCv, image);
        return new ResponseEntity<>(mapper.cvToCvResponse(cv), HttpStatus.CREATED);
    }

    // 이력서 수정
    @PatchMapping("/edit/{cv-id}")
    public ResponseEntity<CvDto.Response> patchCv(@PathVariable("cv-id") long cvId,
                                  @RequestBody @Valid CvDto.Patch requestBody) {

        Cv patchCv = mapper.cvPatchToCv(requestBody);
        Cv cv = cvService.updateCv(patchCv);

        return new ResponseEntity<>(mapper.cvToCvResponse(cv), HttpStatus.OK);
    }

    // 이력서 조회
    @GetMapping("/{cv-id}")
    public ResponseEntity<CvDto.Response> getCv(@PathVariable("cv-id") long cvId) {

        Cv cv = cvService.getCv(cvId);

        return new ResponseEntity<>(mapper.cvToCvResponse(cv), HttpStatus.OK);
    }

    // 이력서 삭제
    @DeleteMapping("/{cv-id}")
    public ResponseEntity<HttpStatus> deleteCv(@PathVariable("cv-id") long cvId) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
