package com.cv.global.auth.jwt.service;

import com.cv.domain.user.entity.User;
import com.cv.domain.user.repository.UserRepository;
import com.cv.global.auth.utils.UserAuthorityUtils;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;
    private final UserAuthorityUtils authorityUtils;
    private final User user;

    public UserDetailsService(UserRepository userRepository, UserAuthorityUtils authorityUtils, User user) {
        this.userRepository = userRepository;
        this.authorityUtils = authorityUtils;
        this.user = user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = userRepository.findByEmail(username);

        // DB에 정보가 없는 회원일 경우, 로그인 실패
        if (findUser == null) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }

        // DB에 정보는 있지만, 탈퇴 상태를 갖고 있는 회원일 경우, 로그인 실패
        user.checkActiveUser(findUser);

        return new UserDetails(findUser);
    }

    private final class UserDetails extends User implements org.springframework.security.core.userdetails.UserDetails {
        UserDetails(User user) {
            setUserId(user.getUserId());
            setEmail(user.getEmail());
            setPassword(user.getPassword());
            setRoles(user.getRoles());
            setName(user.getName());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
