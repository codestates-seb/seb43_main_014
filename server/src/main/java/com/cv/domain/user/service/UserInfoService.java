package com.cv.domain.user.service;

import com.cv.domain.user.dto.mypage.ProfileImageDto;
import com.cv.domain.user.dto.mypage.UserPasswordPatchDto;
import com.cv.domain.user.dto.mypage.UserPatchDto;
import com.cv.domain.user.dto.mypage.UserPatchResponseDto;
import com.cv.domain.user.entity.User;
import com.cv.domain.user.mapper.UserMapper;
import com.cv.domain.user.repository.UserRepository;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
@Transactional
public class UserInfoService implements UserInfoServiceInterface {
    private final UserServiceUtilsInterface userServiceUtilsInterface;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper mapper;


    @Override
    public LocalDate changePassword(String uuid, UserPasswordPatchDto userPasswordPatchDto) {
        User loggedInUser = userServiceUtilsInterface.findUserByUUID(uuid);
        String currentPassword = userPasswordPatchDto.getCurrentPassword();
        String newPassword = userPasswordPatchDto.getNewPassword();

        loggedInUser.checkActiveUser(loggedInUser);
        String loggedInUserPassword = loggedInUser.getPassword();

        if (passwordEncoder.matches(currentPassword, loggedInUserPassword)) {
            loggedInUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(loggedInUser);
        } else {
            throw new BusinessLogicException(ExceptionCode.PASSWORD_MISMATCH);
        }
        return loggedInUser.getModifiedAt().toLocalDate();
    }

    @Override
    public UserPatchResponseDto uploadProfile(String uuid, ProfileImageDto profileImageDto) {
        User loggedInUser = userServiceUtilsInterface.findUserByUUID(uuid);
        loggedInUser.checkActiveUser(loggedInUser);
        loggedInUser.setProfileImage(profileImageDto.getProfileImage());
        userRepository.save(loggedInUser);
        return mapper.userPatchToResponse(loggedInUser);
    }

    @Override
    public UserPatchResponseDto updateUserInfo(String uuid, UserPatchDto userInfoPatchDto) {
        User loggedInUser = userServiceUtilsInterface.findUserByUUID(uuid);
        loggedInUser.checkActiveUser(loggedInUser);

        if(!userInfoPatchDto.getName().equals(loggedInUser.getName())){ loggedInUser.setName(userInfoPatchDto.getName());}
        if(!userInfoPatchDto.getPhone().equals(loggedInUser.getPhone())){loggedInUser.setPhone(userInfoPatchDto.getPhone());}
        User updatedUserInfo = userRepository.save(loggedInUser);
        return mapper.userPatchToResponse(updatedUserInfo);
    }
}
