package com.cv.domain.user.dto;

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
