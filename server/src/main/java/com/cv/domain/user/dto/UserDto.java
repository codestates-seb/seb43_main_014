package com.cv.domain.user.dto;

import com.cv.domain.user.validator.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class UserDto {
    @AllArgsConstructor
    @Data
    public static class Post {
        @NotBlank(message = "이름은 공백이 아니어야 합니다.")
        private String name;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com$",
                message = "올바른 이메일을 작성해주세요.")
        @ValidEmail(message = "이메일은 중복될 수 없습니다.")
        private String email;

        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
        message = "패스워드는 최소 8글자 이상이며, 적어도 하나의 알파벳 문자, 하나의 숫자, 하나의 특수 문자를 포함해야 합니다.")
        private String password;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
        message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
        private String phone;
    }

    @Data
    public static class Patch {
        @NotBlank(message = "이름은 공백이 아니어야 합니다.")
        private String name;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
        private String phone;
    }


    @Data
    public static class PasswordPatch{
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
                message = "패스워드는 최소 8글자 이상이며, 적어도 하나의 알파벳 문자, 하나의 숫자, 하나의 특수 문자를 포함해야 합니다.")
        private String currentPassword;

        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
                message = "패스워드는 최소 8글자 이상이며, 적어도 하나의 알파벳 문자, 하나의 숫자, 하나의 특수 문자를 포함해야 합니다.")
        private String newPassword;
    }

    @Data
    public static class PasswordGet {
        private String email;
    }

    @Data
    public static class SignUpResponse{
        private Long userId;
    }

    @Data
    public static class UserPatchResponse{
        private String name;
        private String email;
        private String phone;
        private String profileImage;
        private LocalDateTime modifiedAt;
    }

    @Data
    public static class ProfileImage{
        private String profileImage;
    }
}
