package com.cv.domain.cv.controller;

import com.cv.domain.cv.dto.cvDto.CvPatchDto;
import com.cv.domain.cv.dto.cvDto.CvPostDto;
import com.cv.domain.cv.dto.cvDto.CvResponseDto;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.mapper.CvMapper;
import com.cv.domain.cv.service.CvService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    private final CvControllerHelper helper;

    // 이력서 작성
    @Operation(summary = "이력서 작성", description = "이력서를 작성합니다",
                responses = {
                        @ApiResponse(responseCode = "201", description = "작성이 완료되었습니다.",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = CvResponseDto.class))),
                        @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                        @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                        @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                        @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                        @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
                })
    @PostMapping
    public ResponseEntity<CvResponseDto> postCv(@RequestBody @Valid CvPostDto requestBody){

        return new ResponseEntity<>(helper.createCvWithTransaction(requestBody), HttpStatus.CREATED);
    }

    // 이력서 수정
    @Operation(summary = "이력서 수정", description = "이력서를 수정합니다",
            responses = {
            @ApiResponse(responseCode = "200", description = "수정이 완료되었습니다.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CvResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
            @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
            @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
            @ApiResponse(responseCode = "404", description = "이력서를 찾을 수 없습니다.", content = @Content()),
            @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
            @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
    })
    @PatchMapping("/edit/{cv-id}")
    public ResponseEntity<CvResponseDto> patchCv(@PathVariable("cv-id") long cvId,
                                                          @RequestBody @Valid CvPatchDto requestBody) {

        return new ResponseEntity<>(helper.updateCvWithTransaction(requestBody), HttpStatus.OK);
    }

    // 이력서 조회
    @Operation(summary = "이력서 조회", description = "이력서를 조회합니다",
            responses = {
                    @ApiResponse(responseCode = "200", description = "정상적으로 조회되었습니다.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CvResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "이력서를 찾을 수 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @GetMapping("/{cv-id}")
    public ResponseEntity<CvResponseDto> getCv(@PathVariable("cv-id") long cvId) {

        return new ResponseEntity<>(helper.getCvWithTransaction(cvId), HttpStatus.OK);
    }

    // 이력서 삭제
    @Operation(summary = "이력서 삭제", description = "이력서를 삭제합니다",
            responses = {
                    @ApiResponse(responseCode = "204", description = "이력서가 삭제되었습니다.", content = @Content()),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "404", description = "이력서를 찾을 수 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @DeleteMapping("/{cv-id}")
    public ResponseEntity<HttpStatus> deleteCv(@PathVariable("cv-id") long cvId) {
        helper.deleteCvWithTransaction(cvId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
