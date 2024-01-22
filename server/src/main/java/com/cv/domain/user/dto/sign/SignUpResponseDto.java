package com.cv.domain.user.dto.sign;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SignUpResponseDto {
    @Schema(description = "회원 식별자", example = "550e8400-e29b-41d4-a716-446655440000")
    private String uuid;
}
