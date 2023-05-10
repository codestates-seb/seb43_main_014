package com.cv.global.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // TODO : 인증 성공 후, 사용자 정보를 response로 전송하는 작업을 여기에 해도 될 것 같음
        // authentication에서 사용자 정보를 얻은 후, HttpServletResponse로 출력 스트림을 생성하여 response를 전송할 수 있음
        log.info("# Authenticated successfully!");
    }
}
