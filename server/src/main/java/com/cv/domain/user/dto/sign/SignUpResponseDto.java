package com.cv.domain.user.dto.sign;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SignUpResponseDto {
    @Schema(description = "회원 식별자", example = "1")
    private Long userId;
}
