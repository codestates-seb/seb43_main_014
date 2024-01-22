package com.cv.global.advice;

import com.cv.global.exception.BusinessLogicException;
import com.cv.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    // @Valid, 바인딩 실패 시 해당 예외 발생
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentException(MethodArgumentNotValidException e) {
        log.error("# handleMethodArgumentException", e);

        return ErrorResponse.of(e.getBindingResult().getFieldErrors());
    }

    // @Validated, 바인딩 실패 시 해당 예외 발생
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        log.error("# handleConstraintViolationException", e);

        return ErrorResponse.of(e.getConstraintViolations());
    }

    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {
        final ErrorResponse response = ErrorResponse.of(e.getExceptionCode());
        log.error("# handleBusinessLogicException", e);

        return new ResponseEntity(response, HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("# handleHttpRequestMethodNotSupportedException", e);

        return ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("# handleHttpMessageNotReadableException", e);

        return ErrorResponse.of(HttpStatus.BAD_REQUEST,
                "Required request body is missing");
    }

    // @RequestParam, required 속성 값 default는 true
    // 요청된 필수 parameter가 결여되었을 때 발생하는 에러
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("# handleMissingServletRequestParameterException", e);

        return ErrorResponse.of(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        log.error("# handle Exception", e);

        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
