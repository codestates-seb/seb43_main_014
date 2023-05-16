package com.cv.domain.cv.dto;

import com.cv.domain.cv.entity.Link;
import lombok.Data;

public class LinkDto {

    @Data
    public static class Add {
        private Link.LinkName linkName;

        private String linkAddress;
    }

    @Data
    public static class Response {
        private long linkId;

        private Link.LinkName linkName;

        private String linkAddress;
    }
}
