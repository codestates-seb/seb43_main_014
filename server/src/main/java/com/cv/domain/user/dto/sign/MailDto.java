package com.cv.domain.user.dto.sign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class MailDto {
    private String address;
    private String title;
    private String message;
}
