package com.cv.domain.user.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ReissueDto {
    @Schema(description = "Access token", example = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoiYWJjQGdtYWl")
    @NotBlank(message = "accessToken을 입력해주세요.")
    private String accessToken;
    @Schema(description = "Refresh token", example = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoiYWJjQGdtYWl")
    @NotBlank(message = "refreshToken을 입력해주세요.")
    private String refreshToken;
}
