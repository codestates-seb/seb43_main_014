package com.cv.domain.user.controller;

import com.cv.domain.user.dto.UserDto;
import com.cv.domain.user.entity.User;
import com.cv.domain.user.mapper.UserMapper;
import com.cv.domain.user.service.UserService;
import com.cv.global.uri.UriCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

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
                                        @PathVariable("userId") Long userId,
                                        @Valid @RequestBody UserDto.PasswordPatch userPasswordPatchDto){
        User user = mapper.userPasswordPatchDtoToUser(userPasswordPatchDto);
        user.setUserId(userId);
        User loggedInUser = (User)authentication.getPrincipal(); // 로그인된 회원의 엔티티 객체가 저장되어있음
        User updatedUserPassword = userService.updateUserPassword(loggedInUser, user);
    }

    // 현재 비밀번호 확인
    @PostMapping
    public boolean 패스워드체크(Authentication authentication,
            @Valid 현재비밀번호가 적힌것){
        userService.현재비밀번호가 맞는지 확인하는 로직 repository.findPassword? -> 일치하는가?
                리턴 true/ false
    }


}
