package com.cv.global.auth.jwt.tokenizer;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

// Jwt 생성(Access Token or Refresh Token)
// Jwt 검증
@Slf4j
@Component
public class JwtTokenizer {
    @Getter
    @Value("${jwt.key}")
    private String secretKey;

    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;

    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String subject, Date expiration, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    // 검증 후, Claims을 반환하는 용도
    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);

        return claims;
    }

    // 토큰을 검증하는 용도
    public String verifySignature(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        // SecurityException : JWT 서명이 일치하지 않음
        // MalformedJwtException : JWT 형식이 올바르지 않음
        // ExpiredJwtException : JWT 유효기간이 지난 경우
        // UnsupportedJwtException : JWT의 토큰 유형이 지원되지 않은 경우
        // IllegalArgumentException : JWT의 클레임 문자열이 비어있는 경우
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws);
            return "success";
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
            return "invalid_token";
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            return "expired_token";
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
            return "unsupported_token";
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty", e);
            return "empty_claims";
        } catch (Exception e) {
            log.info("Exception", e);
            return "exception";
        }
    }

    // 만료된 JWT에서 claims를 반환하는 용도
    public Claims getClaimsFromExpiredJws(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jws)
                    .getBody();

            return claims;
        } catch (ExpiredJwtException e) {
            Claims claims = e.getClaims();

            // 만료된 JWT의 claims를 추출하여 반환
            return claims;
        }
    }
    
    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }

    // JWS의 남은 유효 시간을 계산하여 반환
    public Long getRemainingValidTime(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Date expiration = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody().getExpiration(); // 만료 시간
        Date now = new Date(); // 현재 시간

        Long remainingMillis = expiration.getTime() - now.getTime(); // getTime()은 현재 시간을 밀리초로 반환
        Long remainingMinutes = remainingMillis / (60 * 1000); // 밀리초를 분으로 변환

        return remainingMinutes;
    }

    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }
}