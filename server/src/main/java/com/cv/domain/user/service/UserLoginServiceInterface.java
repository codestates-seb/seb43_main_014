package com.cv.domain.user.service;

import com.cv.domain.user.dto.login.ReissueDto;
import com.cv.domain.user.dto.login.ReissueResponseDto;
import com.cv.domain.user.dto.login.TokenInfoDto;
import com.cv.domain.user.dto.logout.LogoutDto;
import com.cv.domain.user.dto.logout.LogoutResponseDto;
import com.cv.domain.user.dto.sign.MailDto;

import java.util.Map;

public interface UserLoginServiceInterface {
    /**
     * <h2>로그아웃을 한다.</h2>
     * </br>
     * @param logout accessToken,refreshToken
     * @return http상태코드, 응답메세지
     */
    LogoutResponseDto logout(LogoutDto logout);

    /**
     * <h2>임시비밀번호 발급 이메일 보내기.</h2>
     * </br>
     * @param mailDto 주소, 타이틀, 메세지
     * @return void
     */
    void sendMail(MailDto mailDto);

    /**
     * <h2>채연님 작성부분.</h2>
     * </br>
     * @param userEmail
     * @return void
     */
    void createMailAndChangePassword(String userEmail);

    /**
     * <h2>access token/refresh token 재발급을 요청한다.</h2>
     * </br>
     * @param reissue name, email, password, phone
     * @return ReissueResponseDto HTTP 상태 코드, 응답 메시지, 새롭게 발급된 Access token/Refresh token
     */
    ReissueResponseDto reissue(ReissueDto reissue);



}
