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
import com.cv.global.auth.utils.UserAuthorityUtils;
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
    private final UserAuthorityUtils authorityUtils;

    public User createUser(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        List<String> roles = authorityUtils.createRoles(user.getEmail());
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        return savedUser;
    }
}


