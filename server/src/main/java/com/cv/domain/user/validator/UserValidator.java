package com.cv.domain.user.validator;

import com.cv.domain.user.entity.User;
import com.cv.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserValidator implements ConstraintValidator<ValidEmail, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(ValidEmail constraint) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (userRepository == null) {
            return true;
        }

        User.UserStatus userStatus = userRepository.findStatusByEmail(email);
        if (userStatus == null || userStatus == User.UserStatus.USER_WITHDRAWN) {
            return true; // 중복된 이메일이 없거나 탈퇴한 회원인 경우 통과
        } else {
            return false; // 중복된 이메일이 존재하여 회원 등록 실패
        }
    }
}
