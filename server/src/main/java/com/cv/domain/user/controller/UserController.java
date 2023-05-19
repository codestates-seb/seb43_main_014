package com.cv.domain.user.controller;

import com.cv.domain.cv.dto.CvDto;
import com.cv.domain.cv.dto.PageLatestCvDto;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.service.CvService;
import com.cv.domain.user.dto.MailDto;
import com.cv.domain.user.dto.UserDto;
import com.cv.domain.user.entity.User;
import com.cv.domain.user.service.DefaultUserService;
import com.cv.domain.user.service.ReadOnlyUserService;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class UserController {
    private final DefaultUserService defaultUserService;
    private final ReadOnlyUserService readOnlyUserService;
    private final CvService cvService;

    // 회원등록
    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody UserDto.Post userPostDto) {
        UserDto.SignUpResponse createdUser = defaultUserService.createUser(userPostDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // 비밀번호 변경
    @PatchMapping("/my-page/password/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public LocalDateTime changePassword(Authentication authentication,
                                        @PathVariable("userId") @Positive Long userId,
                                        @Valid @RequestBody UserDto.PasswordPatch userPasswordPatchDto) {
        return defaultUserService.changePassword(userId, userPasswordPatchDto);
    }

    // 이름, 휴대번호 변경
    @PatchMapping("/my-page/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity updateUser(@PathVariable("userId") @Positive Long userId,
                                     @Valid @RequestBody UserDto.Patch userInfoPatchDto) {
        UserDto.UserPatchResponse updatedUser = defaultUserService.updateUserInfo(userId, userInfoPatchDto);
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
    public ResponseEntity<UserDto.UserPatchResponse> uploadProfileImage(@PathVariable("userId") Long userId,
                                                   @RequestBody UserDto.ProfileImage profileImageDto) {
        UserDto.UserPatchResponse updatedUser = defaultUserService.uploadProfile(userId, profileImageDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // 비밀번호 찾기
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody UserDto.PasswordGet passwordGet) {
        defaultUserService.createMailAndChangePassword(passwordGet.getEmail());
        return ResponseEntity.ok().build();
    }

    // email 중복확인
    @PostMapping("/sign/email")
    public boolean isEmailDuplicated(@RequestBody UserDto.Email userEmailDto){
        return defaultUserService.isEmailDuplicated(userEmailDto);
    }

    // 휴대폰번호 중복확인
    @PostMapping("/sign/phone")
    public boolean isPhoneDuplicated(@RequestBody UserDto.Phone userPhoneDto){
        return defaultUserService.isPhoneDuplicated(userPhoneDto);

    }
}