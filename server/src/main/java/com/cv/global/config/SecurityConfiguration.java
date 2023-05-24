package com.cv.global.config;

import com.cv.global.auth.jwt.filter.JwtAuthenticationFilter;
import com.cv.global.auth.jwt.filter.JwtVerificationFilter;
import com.cv.global.auth.jwt.handler.UserAccessDeniedHandler;
import com.cv.global.auth.jwt.handler.UserAuthenticationEntryPoint;
import com.cv.global.auth.jwt.handler.UserAuthenticationFailureHandler;
import com.cv.global.auth.jwt.handler.UserAuthenticationSuccessHandler;
import com.cv.global.auth.jwt.service.UserDetailsService;
import com.cv.global.auth.jwt.tokenizer.JwtTokenizer;
import com.cv.global.auth.oauth2.handler.OAuth2UserSuccessHandler;
import com.cv.global.auth.oauth2.service.CustomOAuth2UserService;
import com.cv.global.auth.utils.UserAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.*;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final UserAuthorityUtils authorityUtils;
    private final OAuth2UserSuccessHandler oAuth2UserSuccessHandler;
    private final CustomOAuth2UserService oAuth2UserService;
    private final UserDetailsService userDetailsService;
    private final RedisTemplate redisTemplate;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new UserAuthenticationEntryPoint())
                .accessDeniedHandler(new UserAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST, "/user").permitAll()  // 회원가입 api는 인증되지 않은 사용자도 호출 가능
                        .antMatchers(HttpMethod.POST, "/user/forgot-password").permitAll()
                        .antMatchers(HttpMethod.POST, "/user/reissue").permitAll()
                        .antMatchers(HttpMethod.POST, "/user/logout").permitAll()
                        .antMatchers(HttpMethod.POST, "/user/sign/email").permitAll()
                        .antMatchers(HttpMethod.POST, "/user/sign/phone").permitAll()
                        .antMatchers(HttpMethod.POST, "/user/**").hasRole("USER")
                        .antMatchers(HttpMethod.PATCH, "/user").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/user/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/user/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.POST, "/cv").hasRole("USER")
                        .antMatchers(HttpMethod.PATCH, "/cv").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "/cv").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/cv/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/cv/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.POST, "/oauth2/phone").hasAnyRole("USER", "ADMIN")
                        .anyRequest().permitAll()
                ) // SecurityContext에 저장된 인증 토큰의 authorities를 기반으로 접근 허용 여부를 결정함
                .oauth2Login()
                .successHandler(oAuth2UserSuccessHandler) // OAuth2 로그인 성공 시 실행되는 handler 설정 (access & refresh token과 함께 redirect 시킬 목적)
                .userInfoEndpoint() // OAuth2 Provider로부터 사용자 정보를 가져오는 엔드포인트 지정
                .userService(oAuth2UserService); // OAuth2 로그인 성공 시 사용자 정보를 처리하기 위한 OAuth2UserService

        return http.build();
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer, redisTemplate);
            jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new UserAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new UserAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils, userDetailsService, redisTemplate);

            builder.addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://main-project-cv-deploy.s3-website.ap-northeast-2.amazonaws.com")); // 프론트 단의 로컬, 운영 origin으로 제한 설정
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE")); // 허용된 HTTP 메서드
        configuration.setAllowedHeaders(Arrays.asList("*")); // 모든 헤더 허용
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Refresh")); // 클라이언트에게 노출되는 응답 헤더
        configuration.setAllowCredentials(true); // 클라이언트 요청 헤더에 인증 정보 포함 가능

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
