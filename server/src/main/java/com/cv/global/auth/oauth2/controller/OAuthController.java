package com.cv.global.auth.oauth2.controller;

import com.cv.domain.user.entity.User;
import com.cv.domain.user.repository.UserRepository;
import com.cv.global.auth.oauth2.dto.PhonePostDto;
import com.cv.global.auth.oauth2.dto.PhonePostResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/oauth2")
@RequiredArgsConstructor
@Tag(name = "OAUTH2", description = "OAUTH2 API Document")
public class OAuthController {
    private final UserRepository userRepository;

    // OAuth2 로그인 시, 휴대전화번호 추가 등록
    @PostMapping("/phone")
    @Operation(summary = "OAuth2 로그인 시, 휴대전화번호 추가 등록", description = "OAuth2 소셜 로그인 시, 휴대전화번호를 추가 등록받습니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "회원의 휴대전화번호가 정상적으로 등록되었습니다.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PhonePostResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content()),
                    @ApiResponse(responseCode = "401", description = "인증 정보가 부족합니다. ex) 로그인이 되어있지 않은 경우", content = @Content()),
                    @ApiResponse(responseCode = "403", description = "요청에 대한 권한이 없습니다.", content = @Content()),
                    @ApiResponse(responseCode = "405", description = "웹 서버에서 요청된 URL에 대해 HTTP 메서드를 허용하지 않습니다.", content = @Content()),
                    @ApiResponse(responseCode = "500", description = "서버에서 문제가 발생했습니다.", content = @Content())
            })
    public ResponseEntity postPhone(Authentication authentication,
                                    @Valid @RequestBody PhonePostDto phoneDto) {
        // FIXME : 현재 OAuth2 인증이니까 Authentication의 Principal이 OAuth2User인 게 정상
        // 현재 Authentication 구성 : Principal(UserDetails) / Crendentials / Collection<GrantedAuthority>
        User user = (User) authentication.getPrincipal();

        User findUser = userRepository.findByEmail(user.getEmail());
        findUser.setPhone(phoneDto.getPhone());
        userRepository.save(findUser);

        PhonePostResponseDto phonePostResponseDto = PhonePostResponseDto
                .builder()
                .userId(findUser.getId())
                .name(findUser.getName())
                .build();

        return new ResponseEntity<>(phonePostResponseDto, HttpStatus.OK);
    }
}
