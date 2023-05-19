package com.cv.global.auth.oauth2.handler;

import com.cv.domain.user.entity.User;
import com.cv.domain.user.repository.UserRepository;
import com.cv.global.auth.jwt.tokenizer.JwtTokenizer;
import com.cv.global.auth.utils.UserAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// OAuth2 인증에 성공하면 호출되는 핸들러
@RequiredArgsConstructor
@Component
public class OAuth2UserSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenizer jwtTokenizer;
    private final UserAuthorityUtils authorityUtils;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        System.out.println("OAuth2UserSuccessHandler 클래스 onAuthenticationSuccess 메서드 : OAuth2User attributes 모두 보기");
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }

        String email = String.valueOf(attributes.get("email"));
        List<String> authorities = authorityUtils.createRoles(email);

        // OAuth2User의 속성들을 토대로 우리 DB에 User 객체를 create하거나 update
        User user = createOrUpdateUser(attributes);

        redirect(request, response, user);
    }

    private User createOrUpdateUser(Map<String, Object> attributes) {
        User createUser = new User();
        User findUser = userRepository.findByEmail((String)attributes.get("email"));

        // 최초 로그인
        if (findUser == null) {
            createUser.setEmail((String)attributes.get("email"));
            createUser.setPassword("");
            createUser.setName((String)attributes.get("name"));
            createUser.setPhone(null);
            createUser.setRoles(authorityUtils.createRoles((String)attributes.get("email")));
            createUser.setProfileImage((String)attributes.get("picture"));

            return userRepository.save(createUser);
        }
        else { // 최초 로그인은 아님 -> OAuth Provider 사이트에서 사용자 정보 변경이 있을 수 있기 때문에 우리 DB에도 update
            findUser.setEmail((String)attributes.get("email"));
            findUser.setName((String)attributes.get("name"));
            findUser.setProfileImage((String)attributes.get("picture"));

            // 코드에서 추가될 정보가 현재 DB에 있는 정보와 동일하다면 Update query가 발생하지 않음
            // 해당 findUser가 이미 영속성 컨텍스트에 로드되어 있다면,
            return userRepository.save(findUser);
        }
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
        String accessToken = delegateAccessToken(user);
        String refreshToken = delegateRefreshToken(user);
        boolean relogin  = true; // 재로그인

        if (user.getPhone() == null) {
            relogin = false; // 처음 로그인
        }

        String uri = createURI(accessToken, refreshToken, relogin).toString();
        getRedirectStrategy().sendRedirect(request, response, uri);
    }

    private String delegateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getEmail());
        claims.put("roles", user.getRoles());

        String subject = user.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationHours());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(User user) {
        String subject = user.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationHours());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }

    private URI createURI(String accessToken, String refreshToken, Boolean relogin) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("accessToken", "Bearer " + accessToken);
        queryParams.add("refreshToken", refreshToken);

        // 재로그인 여부에 따라 redirect uri를 분기
        String path;
        if (relogin) {
            path = "/login/oauth2/already";
        }
        else {
            path = "/login/oauth2";
        }

        // FIXME: 배포 시, 웹 서버 호스팅 도메인으로 바꿔야함
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(3000)
                .path(path)
                .queryParams(queryParams)
                .build()
                .toUri();
    }
}
