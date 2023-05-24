package com.cv.domain.user.dto.sign;

import com.cv.domain.user.validator.ValidEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostDto {
    @Schema(description = "이름", example = "홍길동")
    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    private String name;

    @Schema(description = "이메일", example = "asd@google.com")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-z]+)+$",
            message = "올바른 이메일을 작성해주세요.")
    @ValidEmail(message = "이메일은 중복될 수 없습니다.")
    private String email;

    @Schema(description = "패스워드", example = "a1234567!")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "패스워드는 최소 8글자 이상이며, 적어도 하나의 알파벳 문자, 하나의 숫자, 하나의 특수 문자를 포함해야 합니다.")
    private String password;

    @Schema(description = "휴대번호", example = "010-1234-5678")
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
    private String phone;
}
