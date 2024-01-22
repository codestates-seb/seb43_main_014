package com.cv.global.auth.jwt.filter;

import com.cv.domain.user.entity.User;
import com.cv.global.auth.jwt.dto.LoginDto;
import com.cv.global.auth.jwt.tokenizer.JwtTokenizer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;
    private final RedisTemplate redisTemplate;

    // 인증 시도
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws ServletException, IOException {
        User user = (User) authResult.getPrincipal();

        String accessToken = delegateAccessToken(user);
        String refreshToken = delegateRefreshToken(user);

        // refreshToken Redis에 저장(expirationTime 설정을 통해 자동 삭제 처리)
        redisTemplate.opsForValue()
                .set("RT_" + user.getEmail(), refreshToken,
                        jwtTokenizer.getRefreshTokenExpirationMinutes(), TimeUnit.MINUTES);

        // JWT token을 response 헤더에 넣어줌
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh", refreshToken);

        LinkedHashMap<String, Object> userInfo = new LinkedHashMap<>();
        userInfo.put("userId", user.getId());
        userInfo.put("name", user.getName());

        Gson gson = new Gson();
        String userInfoJson = gson.toJson(userInfo);

        // 로그인 사용자 정보(userId, name)를 response body에 넣어줌
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(userInfoJson);

        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);

        // AuthenticationFailureHandler의 onAuthenticationFailure()는 알아서 호출됨
    }

    private String delegateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getEmail());
        claims.put("roles", user.getRoles());

        String subject = user.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(User user) {
        String subject = user.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}
