package com.cv.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    USER_NOT_FOUND(404, "User not found"),
    USER_EXISTS(409, "User not exists"),
    RESUME_NOT_FOUND(404, "Resume not found");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
