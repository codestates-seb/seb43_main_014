package com.cv.global.config;

import com.cv.domain.user.service.UserService;
import com.cv.global.auth.filter.JwtAuthenticationFilter;
import com.cv.global.auth.filter.JwtVerificationFilter;
import com.cv.global.auth.handler.*;
import com.cv.global.auth.jwt.JwtTokenizer;
import com.cv.global.auth.utils.UserAuthorityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.*;

@Configuration
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final UserAuthorityUtils authorityUtils;
    private final UserService userService;

    public SecurityConfiguration(@Lazy JwtTokenizer jwtTokenizer, @Lazy UserAuthorityUtils authorityUtils, @Lazy UserService userService) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.userService = userService;
    }

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
//                        .antMatchers(HttpMethod.POST, "/user").permitAll()  // 회원가입 api는 인증되지 않은 사용자도 호출 가능
//                        .antMatchers(HttpMethod.PATCH, "/user").hasRole("USER")
//                        .antMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
//                        .antMatchers(HttpMethod.GET, "/user/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers(HttpMethod.DELETE, "/user/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers(HttpMethod.POST, "/cv").hasRole("USER")
//                        .antMatchers(HttpMethod.PATCH, "/cv").hasRole("USER")
//                        .antMatchers(HttpMethod.GET, "/cv").hasRole("ADMIN")
//                        .antMatchers(HttpMethod.GET, "/cv/**").hasAnyRole("USER", "ADMIN")
//                        .antMatchers(HttpMethod.DELETE, "/cv/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().permitAll() // 인증되지 않은 요청은 보호되는 리소스에 접근할 수 없음(모든 요청에 대해 인증 요구)
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(new OAuth2UserSuccessHandler(jwtTokenizer, authorityUtils, userService)));

        return http.build();
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new UserAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new UserAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder.addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class)
                    .addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // TODO : 프론트 단의 origin으로 제한 설정해야함
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE")); // 허용된 HTTP 메서드
        configuration.setAllowedHeaders(Arrays.asList("*")); // 모든 헤더 허용
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Refresh")); // 클라이언트에게 노출되는 응답 헤더
        configuration.setAllowCredentials(true); // 클라이언트 요청 헤더에 인증 정보 포함 가능


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
