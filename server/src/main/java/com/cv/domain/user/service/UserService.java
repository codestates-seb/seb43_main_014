package com.cv.domain.user.service;

import javax.transaction.Transactional;

import com.cv.domain.user.entity.User;
import com.cv.domain.user.repository.UserRepository;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import com.cv.global.auth.utils.UserAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthorityUtils authorityUtils;

    public User createUser(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        List<String> roles = authorityUtils.createRoles(user.getEmail());
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        return savedUser;
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
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return foundUser;
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

    public User foundEmail(String email) {
        User loginUser = userRepository.findByEmail(email);
        return loginUser;
    }
}


