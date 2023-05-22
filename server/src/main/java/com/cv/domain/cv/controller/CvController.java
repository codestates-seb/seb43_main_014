package com.cv.domain.cv.controller;

import com.cv.domain.cv.dto.CvDto;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.mapper.CvMapper;
import com.cv.domain.cv.service.CvService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@Tag(name = "CV", description = "CV API Document")
@RequestMapping("/cv")
@RestController
public class CvController {

    private final CvMapper mapper;
    private final CvService cvService;
    private final CvControllerHelper helper;

    // 이력서 작성
    @Operation(summary = "이력서 작성", description = "이력서를 작성합니다",
                responses = {
                        @ApiResponse(responseCode = "201", description = "작성이 완료되었습니다.",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = CvDto.Response.class))),
                        @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                        @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                        @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                        @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                        @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
                })
    @PostMapping
    public ResponseEntity<CvDto.Response> postCv(@RequestBody @Valid CvDto.Post requestBody){
        Cv postCv = mapper.cvPostToCv(requestBody);
        Cv cv = cvService.createCv(postCv);
        cvService.injectLowDomain(cv);
        CvDto.Response response = mapper.cvToCvResponse(cv);
//        CvDto.Response response = helper.createCvHelper(requestBody);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 이력서 수정
    @Operation(summary = "이력서 수정", description = "이력서를 수정합니다",
            responses = {
            @ApiResponse(responseCode = "200", description = "수정이 완료되었습니다.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CvDto.Response.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
            @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
            @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
            @ApiResponse(responseCode = "404", description = "이력서를 찾을 수 없습니다.", content = @Content()),
            @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
            @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
    })
    @PatchMapping("/edit/{cv-id}")
    public ResponseEntity<CvDto.Response> patchCv(@PathVariable("cv-id") long cvId,
                                  @RequestBody @Valid CvDto.Patch requestBody) {

        Cv patchCv = mapper.cvPatchToCv(requestBody);
        Cv cv = cvService.updateCv(patchCv);

        return new ResponseEntity<>(mapper.cvToCvResponse(cv), HttpStatus.OK);
    }

    // 이력서 조회
    @Operation(summary = "이력서 조회", description = "이력서를 조회합니다",
            responses = {
                    @ApiResponse(responseCode = "200", description = "정상적으로 조회되었습니다.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CvDto.Response.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "이력서를 찾을 수 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @GetMapping("/{cv-id}")
    public ResponseEntity<CvDto.Response> getCv(@PathVariable("cv-id") long cvId) {

        Cv cv = cvService.getCv(cvId);

        return new ResponseEntity<>(mapper.cvToCvResponse(cv), HttpStatus.OK);
    }

    // 이력서 삭제
    @Operation(summary = "이력서 삭제", description = "이력서를 삭제합니다",
            responses = {
                    @ApiResponse(responseCode = "204", description = "작성에 성공하셨습니다.", content = @Content()),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "이력서를 찾을 수 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @DeleteMapping("/{cv-id}")
    public ResponseEntity<HttpStatus> deleteCv(@PathVariable("cv-id") long cvId) {
        cvService.deleteCv(cvId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
