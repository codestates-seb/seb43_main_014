package com.cv.domain.user.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReissueResponseDto {
    @Schema(description = "HTTP 상태 코드", example = "200")
    private int state;
    @Schema(description = "응답 메시지", example = "Token information has been updated")
    private String message;
    @Schema(description = "새롭게 발급된 Access token/Refresh token", example = "{\"accessToken\" : \"eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1\", \"refreshToken\" : \"eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1\"}")
    private Object data;
}
