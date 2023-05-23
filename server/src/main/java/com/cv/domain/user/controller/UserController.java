package com.cv.domain.user.controller;

import com.cv.domain.cv.dto.PageLatestCvDto;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.service.CvService;
import com.cv.domain.user.dto.login.*;
import com.cv.domain.user.dto.logout.LogoutDto;
import com.cv.domain.user.dto.logout.LogoutResponseDto;
import com.cv.domain.user.dto.mypage.ProfileImageDto;
import com.cv.domain.user.dto.mypage.UserPasswordPatchDto;
import com.cv.domain.user.dto.mypage.UserPatchDto;
import com.cv.domain.user.dto.mypage.UserPatchResponseDto;
import com.cv.domain.user.dto.sign.SignUpResponseDto;
import com.cv.domain.user.dto.sign.UserPostDto;
import com.cv.domain.user.entity.User;
import com.cv.domain.user.service.DefaultUserService;
import com.cv.domain.user.service.ReadOnlyUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@Validated
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "USER", description = "USER API Document")
public class UserController {
    private final DefaultUserService defaultUserService;
    private final ReadOnlyUserService readOnlyUserService;
    private final CvService cvService;

    // 회원등록
    @Operation(summary = "회원등록", description = "회원을 등록합니다",
            responses = {
                    @ApiResponse(responseCode = "201", description = "등록이 완료되었습니다.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SignUpResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody UserPostDto userPostDto) {
        SignUpResponseDto createdUser = defaultUserService.createUser(userPostDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // access token/refresh token 재발급
    @PostMapping("/reissue")
    public ResponseEntity reissue(@Valid @RequestBody ReissueDto reissue) {
        ReissueResponseDto reissueResponse = defaultUserService.reissue(reissue);

        return new ResponseEntity<>(reissueResponse, HttpStatus.valueOf(reissueResponse.getState()));
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity logout(@Valid @RequestBody LogoutDto logout) {
        LogoutResponseDto logoutResponse = defaultUserService.logout(logout);

        return new ResponseEntity<>(logoutResponse, HttpStatus.valueOf(logoutResponse.getState()));
    }

    // 비밀번호 변경
    @PatchMapping("/my-page/password/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public LocalDateTime changePassword(Authentication authentication,
                                        @PathVariable("userId") @Positive Long userId,
                                        @Valid @RequestBody UserPasswordPatchDto userPasswordPatchDto) {
        return defaultUserService.changePassword(userId, userPasswordPatchDto);
    }

    // 이름, 휴대번호 변경
    @PatchMapping("/my-page/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity updateUser(@PathVariable("userId") @Positive Long userId,
                                     @Valid @RequestBody UserPatchDto userInfoPatchDto) {
        UserPatchResponseDto updatedUser = defaultUserService.updateUserInfo(userId, userInfoPatchDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // 계정삭제
    @DeleteMapping("/my-page/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity deleteUser(@PathVariable("userId") @Positive Long userId) {
        defaultUserService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 마이페이지 조회
    @GetMapping("/my-page/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<Map<String, Object>> getUserProfile(@PathVariable("userId") @Positive Long userId,
                                                              @RequestParam(name = "page", defaultValue = "1") int page) {
        User user = readOnlyUserService.findUser(userId);
        Page<Cv> cvPage = cvService.findLatestCvsByUser(userId, page);
        PageLatestCvDto latestCvDto = new PageLatestCvDto(cvPage);

        Map<String, Object> result = new HashMap<>();
        result.put("profileImage", user.getProfileImage());
        result.put("name", user.getName());
        result.put("email", user.getEmail());
        result.put("phone", user.getPhone());
        result.put("cvs", latestCvDto);

        return ResponseEntity.ok(result);
    }

    // 이력서 페이지네이션
    @GetMapping("/my-page/{userId}/cvs")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<PageLatestCvDto> getLatestCvsByUser(@PathVariable("userId") Long userId,
                                                              @RequestParam(name = "page", defaultValue = "1") int page) {
        Page<Cv> cvPage = cvService.findLatestCvsByUser(userId, page);
        PageLatestCvDto latestCvDto = new PageLatestCvDto(cvPage);
        return ResponseEntity.ok(latestCvDto);
    }

    // 프로필이미지 등록
    @PostMapping("/my-page/{userId}/profile-image")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<UserPatchResponseDto> uploadProfileImage(@PathVariable("userId") Long userId,
                                                                   @RequestBody ProfileImageDto profileImageDto) {
        UserPatchResponseDto updatedUser = defaultUserService.uploadProfile(userId, profileImageDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // 비밀번호 찾기
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody PasswordGetDto passwordGet) {
        defaultUserService.createMailAndChangePassword(passwordGet.getEmail());
        return ResponseEntity.ok().build();
    }

    // email 중복확인
    @PostMapping("/sign/email")
    public boolean isEmailDuplicated(@RequestBody EmailDto userEmailDto) {
        return readOnlyUserService.isEmailDuplicated(userEmailDto);
    }

    // 휴대폰번호 중복확인
    @PostMapping("/sign/phone")
    public boolean isPhoneDuplicated(@RequestBody PhoneDto userPhoneDto) {
        return readOnlyUserService.isPhoneDuplicated(userPhoneDto);
    }
}
