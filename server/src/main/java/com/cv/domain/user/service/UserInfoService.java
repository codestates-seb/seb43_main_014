package com.cv.domain.user.service;

import com.cv.domain.cv.dto.PageLatestCvDto;
import com.cv.domain.cv.entity.Cv;
import com.cv.domain.cv.service.CvService;
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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class UserInfoService implements UserInfoServiceInterface {
    private final UserServiceUtilsInterface userServiceUtilsInterface;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final CvService cvService;
    private final UserServiceUtilsInterface serviceUtils;


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

    @Override
    public ResponseEntity<Map<String, Object>> getUserProfile(String uuid, int page) {
        Page<Cv> cvPage = cvService.findLatestCvsByUser(uuid, page);
        PageLatestCvDto latestCvDto = new PageLatestCvDto(cvPage);
        User user = serviceUtils.findUserByUUID(uuid);

        Map<String, Object> result = new HashMap<>();
        result.put("profileImage", user.getProfileImage());
        result.put("name", user.getName());
        result.put("email", user.getEmail());
        result.put("phone", user.getPhone());
        result.put("createdAt", user.getCreatedAt());
        result.put("modifiedAt", user.getModifiedAt().toLocalDate().toString().substring(0, 10));
        result.put("cvs", latestCvDto);

        return ResponseEntity.ok(result);
    }


    ResponseEntity<PageLatestCvDto> getLatestCvsByUser(String uuid, int page){
        Page<Cv> cvPage = cvService.findLatestCvsByUser(uuid, page);
        PageLatestCvDto latestCvDto = new PageLatestCvDto(cvPage);
        return ResponseEntity.ok(latestCvDto);
    }
}
