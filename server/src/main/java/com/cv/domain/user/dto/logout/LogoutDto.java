package com.cv.domain.user.dto.logout;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LogoutDto {
    @Schema(description = "Access token", example = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoiYWJjQGdtYWl")
    @NotBlank(message = "잘못된 요청입니다. accessToken을 입력해주세요.")
    private String accessToken;
    @Schema(description = "Refresh token", example = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoiYWJjQGdtYWl")
    @NotBlank(message = "잘못된 요청입니다. refreshToken을 입력해주세요.")
    private String refreshToken;
}
