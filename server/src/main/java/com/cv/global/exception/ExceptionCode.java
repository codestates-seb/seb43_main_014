package com.cv.global.exception;

import lombok.Getter;

public enum ExceptionCode {
    /*
    TODO : 예외 코드 필요 시 상황에 맞체 추가할 것
        - 401 Unauthorized, 403 Forbidden, 404 Not Found
        - 500 Internal Server Error, 502 Bad Gateway
     */

    USER_NOT_FOUND(404, "User not found"),
    USER_EXISTS(409, "Member exists"),
    RESUME_NOT_FOUND(404, "Resume not found"),
    RESUME_WAS_DELETED(404, "Resume was deleted"),
    SKILL_STACK_NOT_FOUND(404, "Skill stack not found"),
    RESUME_EXISTS(409, "Resume exists"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    PASSWORD_MISMATCH(401,"Password mismatch" );


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
