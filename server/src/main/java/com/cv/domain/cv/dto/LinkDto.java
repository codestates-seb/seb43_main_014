package com.cv.domain.cv.dto;

import com.cv.domain.cv.entity.Link;
import lombok.Getter;

public class LinkDto {

    @Getter
    public static class Add {
        private Link.LinkName linkName;

        private String linkAddress;
    }

    @Getter
    public static class Response {
        private long linkId;

        private Link.LinkName linkName;

        private String linkAddress;
    }
}
