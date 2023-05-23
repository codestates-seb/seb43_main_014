package com.cv.domain.user.dto.mypage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserPatchResponseDto {
    @Schema(description = "이름", example = "홍길동")
    private String name;
    @Schema(description = "이메일", example = "asd@google.com")
    private String email;
    @Schema(description = "휴대번호", example = "010-1234-5678")
    private String phone;
    @Schema(description = "프로필 이미지", example = "user/profile/image/smile")
    private String profileImage;
    @Schema(description = "프로필 이미지", example = "2023-05-23")
    private LocalDate modifiedAt;
    @Schema(description = "회원가입 일자", example = "2023-05-23")
    private LocalDate createdAt;
}
