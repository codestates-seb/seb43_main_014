package com.cv.global.auth.oauth2.controller;

import com.cv.domain.user.entity.User;
import com.cv.domain.user.repository.UserRepository;
import com.cv.global.auth.oauth2.dto.OAuthPhoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth2")
@RequiredArgsConstructor
public class OAuthController {
    private final UserRepository userRepository;
    @PostMapping("/phone")
    public ResponseEntity postPhone(Authentication authentication,
                                    @Valid @RequestBody OAuthPhoneDto phoneDto) {
        // FIXME : 현재 OAuth2 인증이니까 Authentication의 Principal이 OAuth2User인 게 정상
        // Authentication 구성 : Principal(username) / Crendentials / Collection<GrantedAuthority>
        String username = (String) authentication.getPrincipal();

        User findUser = userRepository.findByEmail(username);
        findUser.setPhone(phoneDto.getPhone());
        userRepository.save(findUser);

        Map<String, Object> userInfo = new LinkedHashMap<>();
        userInfo.put("userId", findUser.getUserId());
        userInfo.put("name", findUser.getName());

        return ResponseEntity.status(HttpStatus.OK).body(userInfo);
    }
}
