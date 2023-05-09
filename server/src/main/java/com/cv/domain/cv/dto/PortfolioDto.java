package com.cv.domain.cv.dto;

import lombok.Getter;

public class PortfolioDto {

    @Getter
    public static class Add {
        private String portfolioAddress;
    }

    @Getter
    public static class Response {
        private long portfolioId;

        private String portfolioAddress;
    }
}
