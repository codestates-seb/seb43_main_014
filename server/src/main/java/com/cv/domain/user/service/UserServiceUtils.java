package com.cv.domain.user.service;

import com.cv.domain.user.dto.login.EmailDto;
import com.cv.domain.user.dto.login.PhoneDto;
import com.cv.domain.user.entity.User;
import com.cv.domain.user.repository.UserRepository;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserServiceUtils implements UserServiceUtilsInterface{
    private final UserRepository userRepository;
    @Override
    public Long findUserIdByUUID(String uuid) {
        Long userId = findUserByUUID(uuid).getUserId();
        if (userId == null) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }
        return userId;
    }
    @Override
    public User findUserByUUID(String uuid) {
        User user = userRepository.findByUuid(uuid);
        if(user == null){
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }
        return user;
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
