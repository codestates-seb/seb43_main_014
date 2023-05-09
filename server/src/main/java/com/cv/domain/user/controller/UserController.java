package com.cv.domain.user.controller;

import com.cv.domain.user.dto.UserDto;
import com.cv.domain.user.entity.User;
import com.cv.domain.user.mapper.UserMapper;
import com.cv.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@Validated
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;
    //private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final static String USER_DEFAULT_URL = "/user";

    @PostMapping
    public ResponseEntity postUser(@Valid @RequestBody UserDto.Post userPostDto){
       // User createdUser = userService.createUser(mapper.userPostDtoToUser(userPostDto));
        return null; //fixme
    }

}
