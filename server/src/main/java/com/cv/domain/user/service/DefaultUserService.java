package com.cv.domain.user.service;

import com.cv.domain.user.dto.login.*;
import com.cv.domain.user.dto.logout.LogoutDto;
import com.cv.domain.user.dto.logout.LogoutResponseDto;
import com.cv.domain.user.dto.mypage.ProfileImageDto;
import com.cv.domain.user.dto.mypage.UserPasswordPatchDto;
import com.cv.domain.user.dto.mypage.UserPatchDto;
import com.cv.domain.user.dto.mypage.UserPatchResponseDto;
import com.cv.domain.user.dto.sign.MailDto;
import com.cv.domain.user.dto.sign.SignUpResponseDto;
import com.cv.domain.user.dto.sign.UserPostDto;
import com.cv.domain.user.entity.User;
import com.cv.domain.user.mapper.UserMapper;
import com.cv.domain.user.repository.UserRepository;
import com.cv.global.auth.jwt.tokenizer.JwtTokenizer;
import com.cv.global.exception.BusinessLogicException;
import com.cv.global.exception.ExceptionCode;
import com.cv.global.auth.utils.UserAuthorityUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RequiredArgsConstructor
@Service
@Transactional
public class DefaultUserService implements UserServiceInter{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthorityUtils authorityUtils;
    private final JavaMailSender javaMailSender;
    private final UserMapper mapper;
    private final ReadOnlyUserService readOnlyUserService;
    private final JwtTokenizer jwtTokenizer;
    private final RedisTemplate redisTemplate;

    @Value("${spring.mail.username}")
    private String adminEmail;

    // 회원등록
    @Override
    public SignUpResponseDto createUser(UserPostDto userPostDto) {
        User user = mapper.userPostDtoToUser(userPostDto);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        List<String> roles = authorityUtils.createRoles(user.getEmail());
        user.setRoles(roles);
        User createdUser = userRepository.save(user);
        return mapper.userPostToSignUpResponse(createdUser);
    }

    // access token/refresh token 재발급 요청
    public ReissueResponseDto reissue(ReissueDto reissue) {
        // Refresh Token 검증
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String rtVerificationResult = jwtTokenizer.verifySignature(reissue.getRefreshToken(), base64EncodedSecretKey);

        if (!(rtVerificationResult).equals("success")) {
            // Refresh Token이 만료되었을 경우 -> 로그인을 새롭게 할 것을 클라이언트에게 알리기 위함
            if (rtVerificationResult.equals("expired_token")) {
                return new ReissueResponseDto(HttpStatus.UNAUTHORIZED.value(), "Refresh Token has expired", null);
            }
            else { // Refresh Token이 유효하지 않거나 잘못된 상태임을 나타냄
                return new ReissueResponseDto(HttpStatus.UNAUTHORIZED.value(), "Refresh Token is not valid", null);
            }
        }

        Claims claims = jwtTokenizer.getClaimsFromExpiredJws(reissue.getAccessToken(), base64EncodedSecretKey);
        String userEmail = (String) claims.get("username");

        // Redis에서 User email을 기반으로 저장된 Refresh Token 값을 가져옴
        String refreshToken = (String) redisTemplate.opsForValue().get("RT_" + userEmail);
        // 로그아웃되어 Redis에 Refresh Token이 존재하지 않는 경우 처리
        // ObjectUtils.isEmpty()는 null과 빈 문자열 체크를 동시에 함
        if (ObjectUtils.isEmpty(refreshToken)) {
            return new ReissueResponseDto(HttpStatus.UNAUTHORIZED.value(), "Refresh Token does not exist due to logout", null);
        }
        if (!refreshToken.equals(reissue.getRefreshToken())) {
            return new ReissueResponseDto(HttpStatus.UNAUTHORIZED.value(), "Refresh Token does not match",null);
        }

        // 새로운 토큰 생성
        TokenInfoDto tokenInfo = generateToken(claims);

        // Refresh Token Redis에 업데이트
        redisTemplate.opsForValue()
                .set("RT_" + userEmail, tokenInfo.getRefreshToken(),
                        jwtTokenizer.getRefreshTokenExpirationMinutes(), TimeUnit.MINUTES);

        return new ReissueResponseDto(HttpStatus.OK.value(), "Token information has been updated", tokenInfo);
    }

    // 토큰 재발급 요청 시, 새로운 access token/refresh token 생성
    private TokenInfoDto generateToken(Map<String, Object> claims) {
        String subject = (String) claims.get("username");
        Date accessTokenExpiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        Date refreshTokenExpiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, accessTokenExpiration, base64EncodedSecretKey);
        String refreshToken = jwtTokenizer.generateRefreshToken(subject, refreshTokenExpiration, base64EncodedSecretKey);

        return new TokenInfoDto(accessToken, refreshToken);
    }

    // 로그아웃
    public LogoutResponseDto logout(LogoutDto logout) {
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String atVerificationResult = jwtTokenizer.verifySignature(logout.getAccessToken(), base64EncodedSecretKey);

        // Access 검증
        if (!(atVerificationResult).equals("success")) {
            return new LogoutResponseDto(HttpStatus.UNAUTHORIZED.value(), "Access Token is not valid");
        }

        // Access Token에서 User email 가져옴
        Map<String, Object> claims = jwtTokenizer.getClaims(logout.getAccessToken(), base64EncodedSecretKey).getBody();
        String userEmail = (String) claims.get("username");

        // Redis에서 해당 User email로 저장된 Refresh Token이 있는지 여부를 확인 후 있을 경우 삭제
        if (redisTemplate.opsForValue().get("RT_" + userEmail) != null) {
            redisTemplate.delete("RT_" + userEmail);
        }

        // 해당 Access Token 유효 시간을 가지고 와서 BlackList로 저장하기
        Long expiration = jwtTokenizer.getRemainingValidTime(logout.getAccessToken(), base64EncodedSecretKey);
        redisTemplate.opsForValue()
                .set(logout.getAccessToken(), "logout", expiration, TimeUnit.MINUTES);

        return new LogoutResponseDto(HttpStatus.OK.value(), "Logout has been successful");
    }

    // 비밀번호 변경
    @Override
    public LocalDateTime changePassword(Long userId, UserPasswordPatchDto userPasswordPatchDto) {
        User loggedInUser = readOnlyUserService.findUser(userId);

        String currentPassword = userPasswordPatchDto.getCurrentPassword();
        String newPassword = userPasswordPatchDto.getNewPassword();

        loggedInUser.checkActiveUser(loggedInUser);
        String loggedInUserPassword = loggedInUser.getPassword();

        if (passwordEncoder.matches(currentPassword, loggedInUserPassword)) {
            loggedInUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(loggedInUser);
        } else {
            throw new BusinessLogicException(ExceptionCode.PASSWORD_MISMATCH);
        }
        return loggedInUser.getModifiedAt();
    }


    // userId가 db에 있는지 확인
    @Override
    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    // 회원의 기본정보(이름,휴대번호변경) 변경
    @Override
    public UserPatchResponseDto updateUserInfo(Long userId, UserPatchDto userInfoPatchDto) {
        User loggedInUser = readOnlyUserService.findUser(userId);
        loggedInUser.checkActiveUser(loggedInUser);

        if(!userInfoPatchDto.getName().equals(loggedInUser.getName())){ loggedInUser.setName(userInfoPatchDto.getName());}
        if(!userInfoPatchDto.getPhone().equals(loggedInUser.getPhone())){loggedInUser.setPhone(userInfoPatchDto.getPhone());}
        User updatedUserInfo = userRepository.save(loggedInUser);
        return mapper.userPatchToResponse(updatedUserInfo);
    }

    // 회원의 활동상태를 탈퇴 상태로 변경
    @Override
    public void deleteUser(Long userId) {
        User loggedInUser = readOnlyUserService.findUser(userId);
        loggedInUser.checkActiveUser(loggedInUser);
        loggedInUser.setUserStatus(User.UserStatus.USER_WITHDRAWN);
        loggedInUser.setDelete(true);
        userRepository.save(loggedInUser);
    }

    // 비밀번호 찾기
    @Override
    public void createMailAndChangePassword(String userEmail) {
        String tempPassword = getTempPassword();
        MailDto mailDto = new MailDto();
        mailDto.setAddress(userEmail);
        mailDto.setTitle("[RocketCV] 임시 비밀번호 안내");
        mailDto.setMessage("안녕하세요. RocketCV 임시 비밀번호 수신을 위해 발송된 메일입니다."
                + System.lineSeparator() +
                "회원님의 임시 비밀번호는 " + tempPassword + " 입니다."
                + System.lineSeparator() +
                "로그인 후에는 새로운 비밀번호로 변경하셔야 합니다."
                + System.lineSeparator() +
                "감사합니다.");
        updatedPassword(tempPassword, userEmail);
        sendMail(mailDto);
    }


    // 임시비밀번호 발급 이메일 보내기
    @Override
    public void sendMail(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        message.setFrom(adminEmail);
        message.setReplyTo(adminEmail);
        javaMailSender.send(message);
    }

    // 이미지 경로 저장하기
    @Override
    public UserPatchResponseDto uploadProfile(Long userId, ProfileImageDto profileImageDto) {
        User loggedInUser = readOnlyUserService.findUser(userId);
        loggedInUser.checkActiveUser(loggedInUser);
        loggedInUser.setProfileImage(profileImageDto.getProfileImage());
        userRepository.save(loggedInUser);
        return mapper.userPatchToResponse(loggedInUser);
    }

    // 현재 DB에 있는 사용자의 비밀번호 -> 임시 비밀번호로 업데이트
    private void updatedPassword(String tempPassword, String userEmail) {
        User findUser = userRepository.findByEmail(userEmail);

        String encryptedPassword = passwordEncoder.encode(tempPassword);
        findUser.setPassword(encryptedPassword);

        userRepository.save(findUser);
    }

    // 임시비밀번호 구문 만들기(최소 1개의 영문자/숫자/특수문자, 10글자)
    private String getTempPassword() {
        String character = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";
        String specialChar = "!@#$%^&";

        SecureRandom random = new SecureRandom();
        StringBuilder passwordBuilder = new StringBuilder();

        // 최소 1개의 영문자
        passwordBuilder.append(character.charAt(random.nextInt(character.length())));

        // 최소 1개의 숫자
        passwordBuilder.append(number.charAt(random.nextInt(number.length())));

        // 최소 1개의 특수문자
        passwordBuilder.append(specialChar.charAt(random.nextInt(specialChar.length())));

        int remainingLength = 10 - passwordBuilder.length();
        for (int i = 0; i < remainingLength; i++) {
            passwordBuilder.append(character.charAt(random.nextInt(character.length())));
        }
        return passwordBuilder.toString();
    }

    public boolean isEmailDuplicated(EmailDto userEmailDto) {
        throw new UnsupportedOperationException("This method is not supported in transaction mode.");
    }

    public boolean isPhoneDuplicated(PhoneDto userPhoneDto) {
        throw new UnsupportedOperationException("This method is not supported in transaction mode.");
    }
}
