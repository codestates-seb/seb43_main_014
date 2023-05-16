package com.cv.domain.user.controller;

import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.service.CvService;
import com.cv.domain.user.dto.MailDto;
import com.cv.domain.user.dto.UserDto;
import com.cv.domain.user.entity.User;
import com.cv.domain.user.mapper.UserMapper;
import com.cv.domain.user.service.UserService;
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
    @PatchMapping("/password/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public void passwordPatch(Authentication authentication,
                            @PathVariable("userId") @Positive Long userId,
                            @Valid @RequestBody UserDto.PasswordPatch userPasswordPatchDto){

        String currentPassword = userPasswordPatchDto.getCurrentPassword();
        String newPassword = userPasswordPatchDto.getNewPassword();

        User user = userService.findUser(userId);

        String email = (String) authentication.getPrincipal();
        User loggedInUser = userService.foundEmail(email);
        userService.changePassword(loggedInUser, user, currentPassword, newPassword);
    }

    // 이름, 휴대번호 변경
    @PatchMapping("/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId") // 로그인한 사용자가 자신의 계정이아닌 다른계정을 삭제할 수 없도록 보안을 강화
    public ResponseEntity patchUser(@PathVariable("userId") @Positive Long userId,
                                    @Valid @RequestBody UserDto.Patch userPatchDto,
                                    Authentication authentication){

        User user = mapper.userPatchDtoToUser(userPatchDto); //TODO 리팩토링 : (멘토링)user객체로 변환할 이유가 없음
        user.setUserId(userId);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity(mapper.userPatchToResponse(updatedUser), HttpStatus.OK);
    }

    // 계정삭제
    @DeleteMapping("/{userId}")
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
//      현재 로그인 사용자와 마이페이지를 조회하려는 사용자가 같은지 이메일로 검증
        String email = (String) authentication.getPrincipal();
        User loginUser = userService.foundEmail(email);
        if (loginUser.getUserId() != userId) {
            System.out.println("Unable to look up My Page");
        }


        User foundUser = userService.findUser(userId);
        if(foundUser == null) {
            return ResponseEntity.notFound().build();
        }

        Pageable pageable = PageRequest.of(page -1, 3, Sort.by("createdAt").descending());
        Page<Cv> cv = cvService.findLatestCvsByUser(userId, pageable); //해당 유저의 최신 이력서 3개씩 페이지네이션 된 목록을 가져옴

        Map<String, Object> result = new HashMap<>();
        result.put("profileImage", foundUser.getProfileImage());
        result.put("name", foundUser.getName());
        result.put("email",foundUser.getEmail());
        result.put("phone", foundUser.getPhone());
        result.put("cvs",cv.getContent()); // (4)
        return ResponseEntity.ok(result);
    }

    //TODO 이미지 수정

    // 비밀번호 찾기
    @PostMapping("/forgot-password")
    public ResponseEntity postUser(Authentication authentication){
        MailDto mailDto = userService.createMailAndChangePassword((String) authentication.getPrincipal());
        userService.sendMail(mailDto);

        return ResponseEntity.ok().build();
    }
}