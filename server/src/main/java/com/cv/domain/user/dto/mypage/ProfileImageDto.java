package com.cv.domain.user.dto.mypage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProfileImageDto {
        @Schema(description = "프로필 이미지", example = "user/profile/image/smile")
        private String profileImage;
}
