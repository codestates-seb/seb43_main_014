package com.cv.domain.user.service;

import com.cv.domain.user.dto.sign.SignUpResponseDto;
import com.cv.domain.user.dto.sign.UserPostDto;

public interface UserSignupServiceInterface {
    /**
     * <h2>회원을 등록한다.</h2>
     * </br>
     * @param userPostDto name, email, password, phone
     * @return uuid:일련의 숫자와 문자로 구성된 128비트 랜덤 문자열
     */
    SignUpResponseDto createUser(UserPostDto userPostDto);

    /**
     * <h2>회원상태를 탈퇴상태로 변경한다.</h2>
     * </br>
     * @param uuid 일련의 숫자와 문자로 구성된 128비트 랜덤 문자열
     * @return void
     */
    void deleteUser(String uuid);


}
