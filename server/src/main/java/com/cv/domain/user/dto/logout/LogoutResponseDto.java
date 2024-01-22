package com.cv.domain.user.dto.logout;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LogoutResponseDto {
        @Schema(description = "HTTP 상태 코드", example = "200")
        private int state;
        @Schema(description = "응답 메시지", example = "Logout has been successful")
        private String message;
}
