package com.cv.global.auth.oauth2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class PhonePostDto {
    @Schema(description = "등록한 휴대전화번호", example = "010-1111-2222")
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
    private String phone;
}
