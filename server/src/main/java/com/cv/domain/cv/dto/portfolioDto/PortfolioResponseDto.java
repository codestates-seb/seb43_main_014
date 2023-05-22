package com.cv.domain.cv.dto.portfolioDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PortfolioResponseDto {
    @Schema(description = "포트폴리오 식별자", example = "1")
    private long portfolioId;

    @Schema(description = "포트폴리오 주소", example = "naver.com(포트폴리오 주소)")
    private String portfolioAddress;
}
