package com.cv.global.auth.jwt.tokenizer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

// Jwt 생성(Access Token or Refresh Token)
// Jwt 검증
@Component
public class JwtTokenizer {
    @Getter
    @Value("${jwt.key}")
    private String secretKey;

    @Getter
    @Value("${jwt.access-token-expiration-hours}")
    private int accessTokenExpirationHours;

    @Getter
    @Value("${jwt.refresh-token-expiration-hours}")
    private int refreshTokenExpirationHours;

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

    // 단순히 검증만 하는 용도로 구현
    public void verifySignature(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
    }

    public Date getTokenExpiration(int expirationHours) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, expirationHours);
        Date expiration = calendar.getTime();

        return expiration;
    }

    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }
}
