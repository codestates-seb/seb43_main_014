package com.cv.domain.user.dto.mypage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
public class UserPasswordPatchDto {
    public UserPasswordPatchDto(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    @Schema(description = "현재 패스워드", example = "a12345671!")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "패스워드는 최소 8글자 이상이며, 적어도 하나의 알파벳 문자, 하나의 숫자, 하나의 특수 문자를 포함해야 합니다.")
    private String currentPassword;

    @Schema(description = "변경할 패스워드", example = "a1234567!")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "패스워드는 최소 8글자 이상이며, 적어도 하나의 알파벳 문자, 하나의 숫자, 하나의 특수 문자를 포함해야 합니다.")
    private String newPassword;
}
