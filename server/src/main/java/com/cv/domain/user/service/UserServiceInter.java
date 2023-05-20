package com.cv.domain.user.service;

import com.cv.domain.user.dto.MailDto;
import com.cv.domain.user.dto.UserDto;
import com.cv.domain.user.entity.User;

import java.time.LocalDateTime;

public interface UserServiceInter {
    UserDto.SignUpResponse createUser(UserDto.Post userPostDto);
    LocalDateTime changePassword(Long userId, UserDto.PasswordPatch userPasswordPatchDto);
    User findUser(Long userId);
    UserDto.UserPatchResponse updateUserInfo(Long userId, UserDto.Patch userInfoPatchDto);
    void deleteUser(Long userId);
    void createMailAndChangePassword(String userEmail);
    void sendMail(MailDto mailDto);
    UserDto.UserPatchResponse uploadProfile(Long userId, UserDto.ProfileImage profileImageDto);
    boolean isEmailDuplicated(UserDto.Email userEmailDto);
    boolean isPhoneDuplicated(UserDto.Phone userPhoneDto);
}
