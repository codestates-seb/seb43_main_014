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

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface UserServiceInter {
    SignUpResponseDto createUser(UserPostDto userPostDto);
    ReissueResponseDto reissue(ReissueDto reissue);
    LogoutResponseDto logout(LogoutDto logout);
    LocalDate changePassword(String uuid, UserPasswordPatchDto userPasswordPatchDto);

    Long findUserIdByUUID(String uuid);

    User findUserByUUID(String uuid);

    UserPatchResponseDto updateUserInfo(String uuid, UserPatchDto userInfoPatchDto);
    void deleteUser(String uuid);
    void createMailAndChangePassword(String userEmail);
    void sendMail(MailDto mailDto);
    UserPatchResponseDto uploadProfile(String uuid, ProfileImageDto profileImageDto);
    boolean isEmailDuplicated(EmailDto userEmailDto);
    boolean isPhoneDuplicated(PhoneDto userPhoneDto);
}
