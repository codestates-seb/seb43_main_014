package com.cv.domain.user.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UserPasswordPatchDto {
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "패스워드는 최소 8글자 이상이며, 적어도 하나의 알파벳 문자, 하나의 숫자, 하나의 특수 문자를 포함해야 합니다.")
    private String currentPassword;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "패스워드는 최소 8글자 이상이며, 적어도 하나의 알파벳 문자, 하나의 숫자, 하나의 특수 문자를 포함해야 합니다.")
    private String newPassword;
}
