package com.cv.domain.user.validator;

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

        return userRepository.findByEmail(email) == null;
    }
}
