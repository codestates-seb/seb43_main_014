package com.cv.domain.user.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PasswordGetDto {
    @Schema(description = "이메일", example = "asd@google.com")
    private String email;
}
