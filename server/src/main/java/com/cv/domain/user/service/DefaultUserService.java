package com.cv.domain.user.service;

import com.cv.domain.user.dto.MailDto;
import com.cv.domain.user.dto.UserDto;
import com.cv.domain.user.entity.User;
import com.cv.domain.user.mapper.UserMapper;
import com.cv.domain.user.repository.UserRepository;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import com.cv.global.auth.utils.UserAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
public class DefaultUserService implements UserServiceInter{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthorityUtils authorityUtils;
    private final JavaMailSender javaMailSender;
    private final UserMapper mapper;
    private final ReadOnlyUserService readOnlyUserService;

    @Value("${spring.mail.username}")
    private String adminEmail;

    // 회원등록
    @Override
    public UserDto.SignUpResponse createUser(UserDto.Post userPostDto) {
        User user = mapper.userPostDtoToUser(userPostDto);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        List<String> roles = authorityUtils.createRoles(user.getEmail());
        user.setRoles(roles);
        User createdUser = userRepository.save(user);
        return mapper.userPostToSignUpResponse(createdUser);
    }

    // 비밀번호 변경
    @Override
    public LocalDateTime changePassword(Long userId, UserDto.PasswordPatch userPasswordPatchDto) {
        User loggedInUser = readOnlyUserService.findUser(userId);

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
        return loggedInUser.getModifiedAt();
    }


    // userId가 db에 있는지 확인
    @Override
    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    // 회원의 기본정보(이름,휴대번호변경) 변경
    @Override
    public UserDto.UserPatchResponse updateUserInfo(Long userId, UserDto.Patch userInfoPatchDto) {
        User loggedInUser = readOnlyUserService.findUser(userId);
        loggedInUser.checkActiveUser(loggedInUser);

        if(!userInfoPatchDto.getName().equals(loggedInUser.getName())){ loggedInUser.setName(userInfoPatchDto.getName());}
        if(!userInfoPatchDto.getPhone().equals(loggedInUser.getPhone())){loggedInUser.setPhone(userInfoPatchDto.getPhone());}
        User updatedUserInfo = userRepository.save(loggedInUser);
        return mapper.userPatchToResponse(updatedUserInfo);
    }

    // 회원의 활동상태를 탈퇴 상태로 변경
    @Override
    public void deleteUser(Long userId) {
        User loggedInUser = readOnlyUserService.findUser(userId);
        loggedInUser.checkActiveUser(loggedInUser);
        loggedInUser.setUserStatus(User.UserStatus.USER_WITHDRAWN);
        loggedInUser.setDelete(true);
        userRepository.save(loggedInUser);
    }

    // 비밀번호 찾기
    @Override
    public void createMailAndChangePassword(String userEmail) {
        String tempPassword = getTempPassword();
        MailDto mailDto = new MailDto();
        mailDto.setAddress(userEmail);
        mailDto.setTitle("[RocketCV] 임시 비밀번호 안내");
        mailDto.setMessage("안녕하세요. RocketCV 임시 비밀번호 수신을 위해 발송된 메일입니다."
                + System.lineSeparator() +
                "회원님의 임시 비밀번호는 " + tempPassword + " 입니다."
                + System.lineSeparator() +
                "로그인 후에는 새로운 비밀번호로 변경하셔야 합니다."
                + System.lineSeparator() +
                "감사합니다.");
        updatedPassword(tempPassword, userEmail);
        sendMail(mailDto);
    }


    // 임시비밀번호 발급 이메일 보내기
    @Override
    public void sendMail(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        message.setFrom(adminEmail);
        message.setReplyTo(adminEmail);
        javaMailSender.send(message);
    }

    // 이미지 경로 저장하기
    @Override
    public UserDto.UserPatchResponse uploadProfile(Long userId, UserDto.ProfileImage profileImageDto) {
        User loggedInUser = readOnlyUserService.findUser(userId);
        loggedInUser.checkActiveUser(loggedInUser);
        loggedInUser.setProfileImage(profileImageDto.getProfileImage());
        userRepository.save(loggedInUser);
        return mapper.userPatchToResponse(loggedInUser);
    }

    // 현재 DB에 있는 사용자의 비밀번호 -> 임시 비밀번호로 업데이트
    private void updatedPassword(String tempPassword, String userEmail) {
        User findUser = userRepository.findByEmail(userEmail);

        String encryptedPassword = passwordEncoder.encode(tempPassword);
        findUser.setPassword(encryptedPassword);

        userRepository.save(findUser);
    }

    // 임시비밀번호 구문 만들기(최소 1개의 영문자/숫자/특수문자, 10글자)
    private String getTempPassword() {
        String character = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";
        String specialChar = "!@#$%^&";

        SecureRandom random = new SecureRandom();
        StringBuilder passwordBuilder = new StringBuilder();

        // 최소 1개의 영문자
        passwordBuilder.append(character.charAt(random.nextInt(character.length())));

        // 최소 1개의 숫자
        passwordBuilder.append(number.charAt(random.nextInt(number.length())));

        // 최소 1개의 특수문자
        passwordBuilder.append(specialChar.charAt(random.nextInt(specialChar.length())));

        int remainingLength = 10 - passwordBuilder.length();
        for (int i = 0; i < remainingLength; i++) {
            passwordBuilder.append(character.charAt(random.nextInt(character.length())));
        }
        return passwordBuilder.toString();
    }

    public boolean isEmailDuplicated(UserDto.Email userEmailDto) {
        boolean isDuplicated = userRepository.existsByEmail(userEmailDto.getEmail());
        return !isDuplicated;
    }

    public boolean isPhoneDuplicated(UserDto.Phone userPhoneDto) {
        boolean isDuplicated = userRepository.existsByPhone(userPhoneDto.getPhone());
        return !isDuplicated;
    }
}
