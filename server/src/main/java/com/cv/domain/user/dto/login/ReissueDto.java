package com.cv.domain.user.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ReissueDto {
    @NotBlank(message = "accessToken을 입력해주세요.")
    private String accessToken;

    @NotBlank(message = "refreshToken을 입력해주세요.")
    private String refreshToken;
}
