package com.cv.domain.cv.dto.linkDto;

import com.cv.domain.cv.entity.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LinkResponseDto {
    @Schema(description = "개인 링크 식별자", example = "1")
    private long linkId;

    @Schema(description = "개인 링크 출처", example = "깃허브")
    private Link.LinkName linkName;

    @Schema(description = "개인 링크 주소", example = "good.github(깃허브 주소)")
    private String linkAddress;
}
