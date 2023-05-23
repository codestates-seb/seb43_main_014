package com.cv.domain.user.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenInfoDto {
    private String accessToken;
    private String refreshToken;
}
