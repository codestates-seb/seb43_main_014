package com.cv.domain.user.service;

import com.cv.domain.user.dto.MailDto;
import com.cv.domain.user.dto.UserDto;
import com.cv.domain.user.entity.User;
import com.cv.domain.user.repository.UserRepository;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReadOnlyUserService implements UserServiceInter {
    private final UserRepository userRepository;

    @Override
    public UserDto.SignUpResponse createUser(UserDto.Post userPostDto) {
        throw new UnsupportedOperationException("This method is not supported in read-only mode.");
    }

    @Override
    public LocalDateTime changePassword(Long userId, UserDto.PasswordPatch userPasswordPatchDto) {
        throw new UnsupportedOperationException("This method is not supported in read-only mode.");
    }

    // userId가 db에 있는지 확인
    @Override
    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }
    @Override
    public UserDto.UserPatchResponse updateUserInfo(Long userId, UserDto.Patch userInfoPatchDto) {
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
    public  UserDto.UserPatchResponse uploadProfile(Long userId, UserDto.ProfileImage profileImageDto) {
        throw new UnsupportedOperationException("This method is not supported in read-only mode.");
    }
}