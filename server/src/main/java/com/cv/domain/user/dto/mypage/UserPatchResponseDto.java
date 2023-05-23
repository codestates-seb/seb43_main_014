package com.cv.domain.user.dto.mypage;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPatchResponseDto {
    private String name;
    private String email;
    private String phone;
    private String profileImage;
    private LocalDateTime modifiedAt;
}
