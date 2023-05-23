package com.cv.domain.user.dto.sign;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class MailDto {
    @Schema(description = "주소", example = "asd@google.com")
    private String address;
    @Schema(description = "타이틀", example = "타이틀 입니다.")
    private String title;
    @Schema(description = "메세지", example = "메세지 입니다.")
    private String message;
}
