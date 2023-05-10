package com.cv.domain.user.service;

//import com.cv.domain.user.entity.User;
//import com.cv.domain.user.repository.UserRepository;
//import com.cv.global.auth.utils.UserAuthorityUtils;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
import javax.transaction.Transactional;

import com.cv.domain.user.entity.User;
import com.cv.domain.user.repository.UserRepository;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
//    private final UserAuthorityUtils authorityUtils;

    public User createUser(User user) {
        User.checkExistMember(user);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

//        List<String> roles = authorityUtils.createRoles(user.getEmail());
//        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        return savedUser;
    }


    //password 변경
    public User updateUserPassword(User loggedInUser, User user) {
        user.checkActiveUser(user);
        User.isMyself(loggedInUser.getUserId(),user.getUserId());
        User findUser = findUser(user.getUserId());
        findUser.setPassword(user.getPassword());

        return userRepository.save(findUser);
    }

    // repository에 userId를 통해 user객체 return
    public User findUser(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        return findUser;
    }


}


