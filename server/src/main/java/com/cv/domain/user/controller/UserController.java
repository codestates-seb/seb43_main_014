package com.cv.domain.user.controller;

import com.cv.domain.cv.dto.CvDto;
import com.cv.domain.cv.dto.PageLatestCvDto;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.service.CvService;
import com.cv.domain.user.dto.MailDto;
import com.cv.domain.user.dto.UserDto;
import com.cv.domain.user.entity.User;
import com.cv.domain.user.mapper.UserMapper;
import com.cv.domain.user.service.UserService;
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
    private final UserService userService;
    private final UserMapper mapper;
    private final CvService cvService;

    // 회원등록
    @PostMapping
    public ResponseEntity postUser(@Valid @RequestBody UserDto.Post userPostDto){
        User createdUser = userService.createUser(mapper.userPostDtoToUser(userPostDto));
        return new ResponseEntity(mapper.userPostToSignUpResponse(createdUser), HttpStatus.CREATED);
    }

    // 비밀번호 변경
    @PatchMapping("/mypage/password/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public LocalDateTime passwordPatch(Authentication authentication,
                                       @PathVariable("userId") @Positive Long userId,
                                       @Valid @RequestBody UserDto.PasswordPatch userPasswordPatchDto){

        User loggedInUser = (User) authentication.getPrincipal();

        String currentPassword = userPasswordPatchDto.getCurrentPassword();
        String newPassword = userPasswordPatchDto.getNewPassword();
        User user = userService.findUser(userId);
        userService.changePassword(loggedInUser, user, currentPassword, newPassword);
        return user.getModifiedAt();
    }

    // 이름, 휴대번호 변경
    @PatchMapping("/mypage/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity patchUser(@PathVariable("userId") @Positive Long userId,
                                    @Valid @RequestBody UserDto.Patch userPatchDto,
                                    Authentication authentication){

        User user = mapper.userPatchDtoToUser(userPatchDto); //TODO 리팩토링 : (멘토링)user객체로 변환할 이유가 없음
        user.setUserId(userId);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity(mapper.userPatchToResponse(updatedUser), HttpStatus.OK);
    }

    // 계정삭제
    @DeleteMapping("/mypage/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity deleteUser(@PathVariable("userId") @Positive Long userId,
                                     Authentication authentication){

        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // 마이페이지 조회
    @GetMapping("/mypage/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity myPage(@PathVariable("userId") @Positive Long userId,
                                 @RequestParam(name = "page", defaultValue = "1") int page,
                                 Authentication authentication){

        User foundUser = userService.findUser(userId);

        Pageable pageable = PageRequest.of(page -1, 3, Sort.by("createdAt").descending());
        Page<Cv> cvPage = cvService.findLatestCvsByUser(userId, pageable); //해당 유저의 최신 이력서 3개씩 페이지네이션 된 목록을 가져옴
        PageLatestCvDto latestCvDto = new PageLatestCvDto(cvPage);

        Map<String, Object> result = new HashMap<>();
        result.put("profileImage", foundUser.getProfileImage());
        result.put("name", foundUser.getName());
        result.put("email",foundUser.getEmail());
        result.put("phone", foundUser.getPhone());
        result.put("cvs",latestCvDto);

        return ResponseEntity.ok(result);
    }

    // 이력서 페이지네이션
    @GetMapping("/mypage/{userId}/cvs")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<?> getLatestCvsByUser(@PathVariable Long userId,
                                                @RequestParam(name = "page", defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page -1, 3, Sort.by("createdAt").descending());
        Page<Cv> cvPage = cvService.findLatestCvsByUser(userId, pageable);
        PageLatestCvDto latestCvDto = new PageLatestCvDto(cvPage);
        return ResponseEntity.ok(latestCvDto);
    }

    // 프로필이미지 등록
    @PostMapping("/mypage/{userId}/profile-image")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity uploadProfileImage(@PathVariable Long userId,
                                            @RequestBody UserDto.ProfileImage profileImageDto,
                                            Authentication authentication) {
        User user = userService.findUser(userId);
        user.setUserId(userId);

        String imagePath = profileImageDto.getProfileImage();
        User updatedUser = userService.uploadProfile(user,imagePath);
        return new ResponseEntity(mapper.userPatchToResponse(updatedUser), HttpStatus.OK);
    }

    // 비밀번호 찾기
    @PostMapping("/forgot-password")
    public ResponseEntity postUser(@RequestBody UserDto.PasswordGet passwordGet){
        MailDto mailDto = userService.createMailAndChangePassword(passwordGet.getEmail());
        userService.sendMail(mailDto);

        return ResponseEntity.ok().build();
    }
}