package com.cv.domain.user.service;


import com.cv.domain.user.dto.login.EmailDto;
import com.cv.domain.user.dto.login.PhoneDto;
import com.cv.domain.user.dto.login.ReissueDto;
import com.cv.domain.user.dto.login.ReissueResponseDto;
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

import java.time.LocalDate;

public interface UserServiceInter {
    /**
     * <h2>회원을 등록한다.</h2>
     * </br>
     * @param userPostDto name, email, password, phone
     * @return uuid:일련의 숫자와 문자로 구성된 128비트 랜덤 문자열
     */
    SignUpResponseDto createUser(UserPostDto userPostDto);

    /**
     * <h2>access token/refresh token 재발급을 요청한다.</h2>
     * </br>
     * @param reissue name, email, password, phone
     * @return ReissueResponseDto HTTP 상태 코드, 응답 메시지, 새롭게 발급된 Access token/Refresh token
     */
    ReissueResponseDto reissue(ReissueDto reissue);

    /**
     * <h2>로그아웃을 한다.</h2>
     * </br>
     * @param logout accessToken,refreshToken
     * @return http상태코드, 응답메세지
     */
    LogoutResponseDto logout(LogoutDto logout);

    /**
     * <h2>비밀번호를 변경한다.</h2>
     * </br>
     * @param uuid 일련의 숫자와 문자로 구성된 128비트 랜덤 문자열
     * @param userPasswordPatchDto 현재 비밀번호, 새 비밀번호
     * @return 비밀번호 최신 업데이트 날짜
     */
    LocalDate changePassword(String uuid, UserPasswordPatchDto userPasswordPatchDto);

    /**
     * <h2>uuid를 통해 userId를 찾는다.</h2>
     * </br>
     * @param uuid 일련의 숫자와 문자로 구성된 128비트 랜덤 문자열
     * @return 일치하는 uuid의 Long타입 userId
     */
    Long findUserIdByUUID(String uuid);

    /**
     * <h2>uuid를 통해 user객체를 찾는다.</h2>
     * </br>
     * @param uuid 일련의 숫자와 문자로 구성된 128비트 랜덤 문자열
     * @return 일치하는 uuid의 User타입 user객체
     */
    User findUserByUUID(String uuid);


    /**
     * <h2>user의 기본정보를 업데이트 한다.</h2>
     * </br>
     * @param uuid 일련의 숫자와 문자로 구성된 128비트 랜덤 문자열
     * @param userInfoPatchDto name, phone
     * @return name, email, phone, profileImage, 마지막 수정일자, 회원가입 일자
     */
    UserPatchResponseDto updateUserInfo(String uuid, UserPatchDto userInfoPatchDto);

    /**
     * <h2>회원상태를 탈퇴상태로 변경한다.</h2>
     * </br>
     * @param uuid 일련의 숫자와 문자로 구성된 128비트 랜덤 문자열
     * @return void
     */
    void deleteUser(String uuid);

    /**
     * <h2>채연님 작성부분.</h2>
     * </br>
     * @param userEmail
     * @return void
     */
    void createMailAndChangePassword(String userEmail);

    /**
     * <h2>임시비밀번호 발급 이메일 보내기.</h2>
     * </br>
     * @param mailDto 주소, 타이틀, 메세지
     * @return void
     */
    void sendMail(MailDto mailDto);

    /**
     * <h2>회원의 프로필사진을 업로드한다.</h2>
     * </br>
     * @param uuid 일련의 숫자와 문자로 구성된 128비트 랜덤 문자열
     * @param profileImageDto profileImage경로를 저장
     * @return name, email, phone, profileImage, 마지막 수정일자, 회원가입 일자
     */
    UserPatchResponseDto uploadProfile(String uuid, ProfileImageDto profileImageDto);

    /**
     * <h2>회원가입 시 이메일이 중복인지 확인한 후 boolean타입으로 클라이언트에게 전달한다.</h2>
     * </br>
     * @param userEmailDto
     * @return 참/거짓
     */
    boolean isEmailDuplicated(EmailDto userEmailDto);

    /**
     * <h2>회원가입 시 휴대번호가 중복인지 확인한 후 boolean타입으로 클라이언트에게 전달한다.</h2>
     * </br>
     * @param userPhoneDto
     * @return 참/거짓
     */
    boolean isPhoneDuplicated(PhoneDto userPhoneDto);
}
