package com.cv.global.auth.oauth2.service;

import com.cv.global.auth.oauth2.attribute.OAuth2Attribute;
import com.cv.global.auth.utils.UserAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

// OAuth2 인증 이후에 인증된 사용자 정보를 처리하는 사용자 서비스
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserAuthorityUtils authorityUtils;

    // 액세스 토큰을 사용하여 사용자 정보를 요청하고 처리
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest); // OAuth 서비스에서 가져온 사용자 정보를 담고 있음

        // userRequest를 통해 사용자의 정보를 추출
        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // OAuth 서비스 이름(ex. google, kakao)
        String userNameAttributeName = userRequest.getClientRegistration() // OAuth 로그인 시 키(pk)가 되는 값 ex) sub(google), id(kakao), id(github)
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        System.out.println("CustomOAuth2UserService 클래스 loadUser 메서드 : OAuth2 Provider에 대한 정보 가져오기");
        System.out.println("Registration Id = " + registrationId);
        System.out.println("UserName AttributeName = " +  userNameAttributeName);

        OAuth2Attribute oAuth2Attribute =
                OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        var userAttribute = oAuth2Attribute.convertToMap();

        // Spring Seucirty의 OAuth2User 구현체를 생성하여 반환(OAuth2UserSuccessHanlder에 사용할 예정)
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), // 사용자가 갖는 권한 TODO : 관리자 권한은 어떻게 처리를 해야할 지
                userAttribute, // OAuth2 공급자로부터 받은 사용자 정보를 담고 잇는 Map 객체
                userNameAttributeName //사용자의 식별자를 나타내는 속성 key값
        );
    }
}
