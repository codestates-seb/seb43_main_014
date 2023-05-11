package com.cv.domain.user.controller;

import com.cv.domain.user.dto.UserDto;
import com.cv.domain.user.entity.User;
import com.cv.domain.user.mapper.UserMapper;
import com.cv.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@Slf4j
@Validated
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    // 회원등록
    @PostMapping
    public ResponseEntity postUser(@Valid @RequestBody UserDto.Post userPostDto){
        User createdUser = userService.createUser(mapper.userPostDtoToUser(userPostDto));
        return new ResponseEntity(mapper.userPostToSignUpResponse(createdUser), HttpStatus.CREATED);
    }

    // 비밀번호 변경
    @PatchMapping("/password/{userId}")
    public void passwordPatch(Authentication authentication,
                                        @PathVariable("userId") @Positive Long userId,
                                        @Valid @RequestBody UserDto.PasswordPatch userPasswordPatchDto){
        User user = mapper.userPasswordPatchDtoToUser(userPasswordPatchDto);
        user.setUserId(userId);
        User loggedInUser = (User)authentication.getPrincipal(); // 로그인된 회원의 엔티티 객체가 저장되어있음
        userService.updateUserPassword(loggedInUser, user);
    }

    // 현재 비밀번호 확인
    @PostMapping("/password/{userId}")
    public boolean verifyCurrentPassword(@PathVariable("userId") @Positive Long userId,
                                        @Valid @RequestBody UserDto.PasswordPost userPasswordPostDto){
        User user = mapper.userPasswordPostDtoToUser(userPasswordPostDto);
        user.setUserId(userId);
        return userService.checkPasswordMatch(user);
    }

    // 이름, 휴대번호 변경
    @PatchMapping("/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId") // 로그인한 사용자가 자신의 계정이아닌 다른계정을 삭제할 수 없도록 보안을 강화
    public ResponseEntity patchUser(@PathVariable("userId") @Positive Long userId,
                                    @Valid @RequestBody UserDto.Patch userPatchDto){
        User user = mapper.userPatchDtoToUser(userPatchDto);
        user.setUserId(userId);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity(mapper.userPatchToResponse(updatedUser), HttpStatus.OK);
    }

    // 계정삭제
    @DeleteMapping("/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity deleteUser(@PathVariable("userId") @Positive Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // 이력서 페이지네이션
    // 마이페이지 전체화면 get
    // 이미지 수정




}
