package com.cv.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    USER_NOT_FOUND(404, "User not found"),
    USER_EXISTS(409, "User not exists"),
    RESUME_NOT_FOUND(404, "Resume not found");

    /*
    TODO : 예외 코드 필요 시 상황에 맞체 추가할 것
        - 401 Unauthorized, 403 Forbidden, 404 Not Found
        - 500 Internal Server Error, 502 Bad Gateway
     */

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
