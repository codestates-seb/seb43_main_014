package com.cv.domain.user.dto.login;

import com.cv.domain.user.validator.ValidEmail;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class EmailDto {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com$",
            message = "올바른 이메일을 작성해주세요.")
    @ValidEmail(message = "이메일은 중복될 수 없습니다.")
    private String email;
}
