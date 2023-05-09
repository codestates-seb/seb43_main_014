package com.cv.global.auth.handler;

//import com.cv.global.auth.utils.ErrorResponder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//// 유효하지 않은 JWT 또는 만료된 JWT로 인해
//// AuthenticationException이 발생할 때 호출되는 핸들러
//@Slf4j
//@Component
//public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        Exception exception = (Exception) request.getAttribute("exception");
//        ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);
//
//        logExceptionMessage(authException, exception);
//    }
//
//    private void logExceptionMessage(AuthenticationException authException, Exception exception) {
//        String message = exception != null ? exception.getMessage() : authException.getMessage();
//        log.warn("Unauthorized error happened : {}", message);
//    }
//}
