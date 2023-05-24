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
import com.cv.domain.user.repository.UserRepository;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReadOnlyUserService implements UserServiceInter {
    private final UserRepository userRepository;

    @Override
    public SignUpResponseDto createUser(UserPostDto userPostDto) {
        throw new UnsupportedOperationException("This method is not supported in read-only mode.");
    }

    @Override
    public ReissueResponseDto reissue(ReissueDto reissue) {
        throw new UnsupportedOperationException("This method is not supported in read-only mode.");
    }

    @Override
    public LogoutResponseDto logout(LogoutDto logout) {
        throw new UnsupportedOperationException("This method is not supported in read-only mode.");
    }

    @Override
    public LocalDate changePassword(Long userId, UserPasswordPatchDto userPasswordPatchDto) {
        throw new UnsupportedOperationException("This method is not supported in read-only mode.");
    }

    // userId가 db에 있는지 확인
    @Override
    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }
    @Override
    public UserPatchResponseDto updateUserInfo(Long userId, UserPatchDto userInfoPatchDto) {
        throw new UnsupportedOperationException("This method is not supported in read-only mode.");
    }

    @Override
    public void deleteUser(Long userId) {
        throw new UnsupportedOperationException("This method is not supported in read-only mode.");
    }

    @Override
    public void createMailAndChangePassword(String userEmail) {
        throw new UnsupportedOperationException("This method is not supported in read-only mode.");
    }

    @Override
    public void sendMail(MailDto mailDto) {
        throw new UnsupportedOperationException("This method is not supported in read-only mode.");
    }

    @Override
    public UserPatchResponseDto uploadProfile(Long userId, ProfileImageDto profileImageDto) {
        throw new UnsupportedOperationException("This method is not supported in read-only mode.");
    }

    @Override
    public boolean isEmailDuplicated(EmailDto userEmailDto) {
        boolean isDuplicated = userRepository.existsByEmail(userEmailDto.getEmail());
        return !isDuplicated;
    }

    @Override
    public boolean isPhoneDuplicated(PhoneDto userPhoneDto) {
        boolean isDuplicated = userRepository.existsByPhone(userPhoneDto.getPhone());
        return !isDuplicated;
    }
}