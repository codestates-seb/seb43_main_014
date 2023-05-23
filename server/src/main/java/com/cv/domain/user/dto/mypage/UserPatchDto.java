package com.cv.domain.user.dto.mypage;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserPatchDto {
    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    private String name;

    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
    private String phone;
}
