package com.cv.domain.user.service;

import javax.transaction.Transactional;

import com.cv.domain.user.dto.MailDto;
import com.cv.domain.user.entity.User;
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

import java.security.SecureRandom;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthorityUtils authorityUtils;
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String adminEmail;

    public User createUser(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        List<String> roles = authorityUtils.createRoles(user.getEmail());
        user.setRoles(roles);
        return userRepository.save(user);
    }


    //password 변경
    public void changePassword(User loggedInUser, User user, String currentPassword, String newPassword) {
        user.checkActiveUser(user);
        User.isMyself(loggedInUser.getUserId(),user.getUserId());

        String loggedInUserPassword = loggedInUser.getPassword();

        if (passwordEncoder.matches(currentPassword,loggedInUserPassword)) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }
    }


    // repository에 userId를 통해 user객체 return
    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }


    // 이름, 휴대번호변경하는 로직
    public User updateUser(User user) {
        user.checkActiveUser(user);
        User foundUser = findUser(user.getUserId());

        foundUser.setName(user.getName());
        foundUser.setPhone(user.getPhone());

        return userRepository.save(foundUser);
    }

    // 멤버의 활동상태를 회원탈퇴 상태로 변경
    public void deleteUser(Long userId) {
        User foundUser = findUser(userId);
        foundUser.checkActiveUser(foundUser);
        foundUser.setUserStatus(User.UserStatus.USER_WITHDRAWN);
        foundUser.setDelete(true);
        userRepository.save(foundUser);
    }

    // 이메일로 user찾기
    public User findUserByEmail(String email) {
        User foundUser = userRepository.findByEmail(email);
        if (foundUser == null) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }
        return foundUser;
    }


//    public void verifyUserEmail(String email, Long userId) {
//        User logginUser = findUserByEmail(email);
//        if (!logginUser.getUserId().equals(userId)) {
//            throw new BusinessLogicException(ExceptionCode.USER_NO_HAVE_AUTHORIZATION);
//        }
//    }

    // 비밀번호 찾기
    public MailDto createMailAndChangePassword(String userEmail) {
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

        return mailDto;
    }

    // 임시비밀번호 발급 이메일 보내기
    public void sendMail(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        message.setFrom(adminEmail);
        message.setReplyTo(adminEmail);
        javaMailSender.send(message);
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

    // 이미지 경로 저장하기
    public User uploadProfile(User user,String profileImage) {
        user.setProfileImage(profileImage);
        return userRepository.save(user);
    }
}


