package com.cv.global.auth.jwt.handler;

import com.cv.global.auth.utils.ErrorResponder;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;

// 유효하지 않은 JWT 또는 만료된 JWT로 인해
// AuthenticationException이 발생할 때 호출되는 핸들러
@Slf4j
@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Exception exception = (Exception) request.getAttribute("exception");

        String message;

        if (exception != null) {
            // 예외 유형에 따라 구체적인 로그 처리
            if (exception instanceof ExpiredJwtException) {
                message = "Access Token has expired";
            }
            else if (exception instanceof SignatureException) {
                // 보안과 데이터 무결성 측면에서, 서명의 유효성을 검증하는 데 발생하는 예외
                message = "Access Token Signature is not valid";
            }
            else { // 로그아웃 상태인 토큰에 대한 에러 처리
                message = exception.getMessage();
            }
        }
        else {
            message = authException.getMessage();
        }

        ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED, message);

        logExceptionMessage(message);
    }

    private void logExceptionMessage(String exceptionMessage) {
        log.warn("Unauthorized error happened : {}", exceptionMessage);
    }
}
