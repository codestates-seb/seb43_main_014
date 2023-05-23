package com.cv.domain.user.dto.logout;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LogoutResponseDto {
        private int state;
        private String message;
}
