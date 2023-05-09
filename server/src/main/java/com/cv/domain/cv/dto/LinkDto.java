package com.cv.domain.cv.dto;

import lombok.Getter;

public class LinkDto {

    @Getter
    public static class Add {
        private String linkName;

        private String linkAddress;
    }

    @Getter
    public static class Response {
        private long linkId;

        private String linkName;

        private String linkAddress;
    }
}
