package com.cv.domain.user.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReissueResponseDto {
    private int state;
    private String message;
    private Object data;
}
