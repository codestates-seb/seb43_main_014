package com.cv.domain.user.dto.sign;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SignUpResponseDto {
    @Schema(description = "회원 식별자", example = "1")
    private Long userId;

    @Schema(description = "회원가입일자", example = "2023-05-23")
    private LocalDate createdAt;
}
