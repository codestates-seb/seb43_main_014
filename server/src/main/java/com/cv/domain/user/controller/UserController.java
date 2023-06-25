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
import com.cv.domain.user.service.UserServiceInter;
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
import java.time.LocalDate;
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
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody UserPostDto userPostDto) {
        SignUpResponseDto createdUser = defaultUserService.createUser(userPostDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // access token/refresh token 재발급
    @Operation(summary = "Jwt 토큰 재발급", description = "Refresh token을 검증하여 새로운 Access token과 Refresh token을 재발급합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "토큰 정보가 성공적으로 업데이트되었습니다.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReissueResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "1. Refresh token이 만료되었습니다. " + "2. Refresh token이 유효하지 않습니다. ex) Jwt 서명 불일치, Jwt 형식/토큰 유형이 올바르지 않음 etc. "
                            + "3. 로그아웃으로 인해 Refresh token이 서버에 존재하지 않습니다. " + "4. Refresh token이 현재 사용자에게 발급된 토큰 정보와 매치되지 않습니다. ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReissueResponseDto.class))),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @PostMapping("/reissue")
    public ResponseEntity reissue(@Valid @RequestBody ReissueDto reissue) {
        ReissueResponseDto reissueResponse = defaultUserService.reissue(reissue);

        return new ResponseEntity<>(reissueResponse, HttpStatus.valueOf(reissueResponse.getState()));
    }

    // 로그아웃
    @Operation(summary = "로그아웃", description = "Access token을 검증하여 로그아웃을 진행합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공적으로 로그아웃되었습니다.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LogoutResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "Access token이 유효하지 않습니다. ex) Jwt 유효기간 만료, 서명 불일치, Jwt 형식/토큰 유형이 올바르지 않음 etc.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LogoutResponseDto.class))),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @PostMapping("/logout")
    public ResponseEntity logout(@Valid @RequestBody LogoutDto logout) {
        LogoutResponseDto logoutResponse = defaultUserService.logout(logout);

        return new ResponseEntity<>(logoutResponse, HttpStatus.valueOf(logoutResponse.getState()));
    }

    // 비밀번호 변경
    @Operation(summary = "비밀번호 변경", description = "회원의 비밀번호를 변경합니다",
            responses = {
                    @ApiResponse(responseCode = "201", description = "변경이 완료되었습니다.",content = @Content()),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @PatchMapping("/my-page/password/{uuid}")
    @PreAuthorize("#uuid == authentication.principal.uuid")
    public LocalDate changePassword(Authentication authentication,
                                    @PathVariable("uuid") String uuid,
                                    @Valid @RequestBody UserPasswordPatchDto userPasswordPatchDto) {
        return defaultUserService.changePassword(uuid, userPasswordPatchDto);
    }

    // 이름, 휴대번호 변경
    @Operation(summary = "이름, 휴대번호 변경", description = "회원의 이름과 휴대번호를 변경합니다",
            responses = {
                    @ApiResponse(responseCode = "201", description = "변경이 완료되었습니다.",content = @Content()),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @PatchMapping("/my-page/{uuid}")
    @PreAuthorize("#uuid == authentication.principal.uuid")
    public ResponseEntity updateUser(@PathVariable("uuid") @Positive String uuid,
                                     @Valid @RequestBody UserPatchDto userInfoPatchDto) {
        UserPatchResponseDto updatedUser = defaultUserService.updateUserInfo(uuid, userInfoPatchDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // 계정삭제
    @Operation(summary = "회원탈퇴", description = "회원을 탈퇴합니다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "회원탈퇴가 완료되었습니다.",content = @Content()),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @DeleteMapping("/my-page/{uuid}")
    @PreAuthorize("#uuid == authentication.principal.uuid")
    public ResponseEntity deleteUser(@PathVariable("uuid") @Positive String uuid) {
        defaultUserService.deleteUser(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 마이페이지 조회
    @Operation(summary = "마이페이지 조회", description = "회원의 정보를 조회합니다",
            responses = {
                    @ApiResponse(responseCode = "201", description = "회원정보를 조회했습니다.",content = @Content()),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @GetMapping("/my-page/{uuid}")
    @PreAuthorize("#uuid == authentication.principal.uuid")
    public ResponseEntity<Map<String, Object>> getUserProfile(@PathVariable("uuid") @Positive String uuid,
                                                              @RequestParam(name = "page", defaultValue = "1") int page) {
        Page<Cv> cvPage = cvService.findLatestCvsByUser(uuid, page);
        PageLatestCvDto latestCvDto = new PageLatestCvDto(cvPage);
        User user = readOnlyUserService.findUserByUUID(uuid);

        Map<String, Object> result = new HashMap<>();
        result.put("profileImage", user.getProfileImage());
        result.put("name", user.getName());
        result.put("email", user.getEmail());
        result.put("phone", user.getPhone());
        result.put("createdAt", user.getCreatedAt());
        result.put("modifiedAt", user.getModifiedAt().toLocalDate().toString().substring(0, 10));
        result.put("cvs", latestCvDto);

        return ResponseEntity.ok(result);
    }

    // 이력서 페이지네이션
    @Operation(summary = "마이페이지 내 이력서 리스트 조회", description = "회원의 이력서 리스트를 조회합니다",
            responses = {
                    @ApiResponse(responseCode = "201", description = "조회가 완료되었습니다.",content = @Content()),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @GetMapping("/my-page/{uuid}/cvs")
    @PreAuthorize("#uuid == authentication.principal.uuid")
    public ResponseEntity<PageLatestCvDto> getLatestCvsByUser(@PathVariable("uuid") String uuid,
                                                              @RequestParam(name = "page", defaultValue = "1") int page) {
        Page<Cv> cvPage = cvService.findLatestCvsByUser(uuid, page);
        PageLatestCvDto latestCvDto = new PageLatestCvDto(cvPage);
        return ResponseEntity.ok(latestCvDto);
    }

    // 프로필이미지 등록
    @Operation(summary = "프로필 이미지 등록", description = "회원의 프로필이미지를 등록합니다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "등록이 완료되었습니다.",content = @Content()),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @PostMapping("/my-page/{uuid}/profile-image")
    @PreAuthorize("#uuid == authentication.principal.uuid")
    public ResponseEntity<UserPatchResponseDto> uploadProfileImage(@PathVariable("uuid") String uuid,
                                                                   @RequestBody ProfileImageDto profileImageDto) {
        UserPatchResponseDto updatedUser = defaultUserService.uploadProfile(uuid, profileImageDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // 비밀번호 찾기
    @Operation(summary = "비밀번호를 찾기", description = "회원의 이메일로 임시 비밀번호를 발급합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "정상적으로 임시 비밀번호가 발급되었습니다.",content = @Content()),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody PasswordGetDto passwordGet) {
        defaultUserService.createMailAndChangePassword(passwordGet.getEmail());
        return ResponseEntity.ok().build();
    }

    // email 중복확인
    @Operation(summary = "회원가입 시 이메일 중복확인", description = "가입 시 이메일을 중복확인 합니다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "확인이 완료되었습니다.",content = @Content()),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @PostMapping("/sign/email")
    public boolean isEmailDuplicated(@RequestBody EmailDto userEmailDto) {
        return readOnlyUserService.isEmailDuplicated(userEmailDto);
    }

    // 휴대폰번호 중복확인
    @Operation(summary = "휴대폰번호 중복확인", description = "가입 시 회원의 휴대번호를 중복확인합니다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "확인이 완료되었습니다.",content = @Content()),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    @PostMapping("/sign/phone")
    public boolean isPhoneDuplicated(@RequestBody PhoneDto userPhoneDto) {
        return readOnlyUserService.isPhoneDuplicated(userPhoneDto);
    }
}
