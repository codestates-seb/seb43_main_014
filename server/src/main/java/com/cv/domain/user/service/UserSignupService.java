package com.cv.domain.user.service;

import com.cv.domain.user.dto.sign.SignUpResponseDto;
import com.cv.domain.user.dto.sign.UserPostDto;
import com.cv.domain.user.entity.User;
import com.cv.domain.user.mapper.UserMapper;
import com.cv.domain.user.repository.UserRepository;
import com.cv.global.auth.utils.UserAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserSignupService implements UserSignupServiceInterface{
    private final UserServiceUtilsInterface userServiceUtilsInterface;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthorityUtils authorityUtils;
    private final UserMapper mapper;


    @Override
    public SignUpResponseDto createUser(UserPostDto userPostDto) {
        User user = mapper.userPostDtoToUser(userPostDto);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        List<String> roles = authorityUtils.createRoles(user.getEmail());
        user.setRoles(roles);
        User createdUser = userRepository.save(user);
        return mapper.userPostToSignUpResponse(createdUser);
    }

    @Override
    public void deleteUser(String uuid) {
        User loggedInUser = userServiceUtilsInterface.findUserByUUID(uuid);
        loggedInUser.checkActiveUser(loggedInUser);
        loggedInUser.setUserStatus(User.UserStatus.USER_WITHDRAWN);
        loggedInUser.setDelete(true);
        userRepository.save(loggedInUser);
    }
}
