package com.cv.domain.user.dto.logout;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LogoutDto {
    @NotBlank(message = "잘못된 요청입니다. accessToken을 입력해주세요.")
    private String accessToken;

    @NotBlank(message = "잘못된 요청입니다. refreshToken을 입력해주세요.")
    private String refreshToken;
}
