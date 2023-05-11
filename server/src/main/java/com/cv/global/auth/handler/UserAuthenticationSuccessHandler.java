package com.cv.global.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// JWTAuthenticationFilter에서 인증에 성공했을 경우 발생하는 핸들러
@Slf4j
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // TODO : Refactor -> 인증 성공 후, JWT 토큰과 사용자 정보를 response로 전송하는 작업을 여기에서 함
        log.info("# Authenticated successfully!");
    }
}
