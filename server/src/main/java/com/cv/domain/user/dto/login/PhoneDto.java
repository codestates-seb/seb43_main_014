package com.cv.domain.user.dto.login;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class PhoneDto {
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
    private String phone;
}
