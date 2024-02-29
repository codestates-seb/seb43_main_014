package com.cv.global.auth.oauth2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PhonePostResponseDto {
    @Schema(description = "OAuth2 로그인된 회원의 인덱스", example = "1")
    private Long userId;
    @Schema(description = "OAuth2 로그인된 회원의 이름", example = "홍길동")
    private String name;
}
