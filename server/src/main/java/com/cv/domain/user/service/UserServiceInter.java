package com.cv.domain.user.service;

import com.cv.domain.user.dto.login.EmailDto;
import com.cv.domain.user.dto.login.PhoneDto;
import com.cv.domain.user.dto.login.ReissueDto;
import com.cv.domain.user.dto.login.ReissueResponseDto;
import com.cv.domain.user.dto.logout.LogoutDto;
import com.cv.domain.user.dto.logout.LogoutResponseDto;
import com.cv.domain.user.dto.mypage.ProfileImageDto;
import com.cv.domain.user.dto.mypage.UserPasswordPatchDto;
import com.cv.domain.user.dto.mypage.UserPatchDto;
import com.cv.domain.user.dto.mypage.UserPatchResponseDto;
import com.cv.domain.user.dto.sign.MailDto;
import com.cv.domain.user.dto.sign.SignUpResponseDto;
import com.cv.domain.user.dto.sign.UserPostDto;
import com.cv.domain.user.entity.User;

import java.time.LocalDateTime;

public interface UserServiceInter {
    SignUpResponseDto createUser(UserPostDto userPostDto);
    ReissueResponseDto reissue(ReissueDto reissue);
    LogoutResponseDto logout(LogoutDto logout);
    LocalDateTime changePassword(Long userId, UserPasswordPatchDto userPasswordPatchDto);
    User findUser(Long userId);
    UserPatchResponseDto updateUserInfo(Long userId, UserPatchDto userInfoPatchDto);
    void deleteUser(Long userId);
    void createMailAndChangePassword(String userEmail);
    void sendMail(MailDto mailDto);
    UserPatchResponseDto uploadProfile(Long userId, ProfileImageDto profileImageDto);
    boolean isEmailDuplicated(EmailDto userEmailDto);
    boolean isPhoneDuplicated(PhoneDto userPhoneDto);
}