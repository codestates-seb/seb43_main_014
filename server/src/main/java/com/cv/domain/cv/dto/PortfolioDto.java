package com.cv.domain.cv.dto;

import lombok.Data;

public class PortfolioDto {

    @Data
    public static class Add {
        private String portfolioAddress;
    }

    @Data
    public static class Response {
        private long portfolioId;

        private String portfolioAddress;
    }
}
