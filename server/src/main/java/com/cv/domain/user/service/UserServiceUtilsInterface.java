package com.cv.domain.user.service;


import com.cv.domain.user.dto.login.EmailDto;
import com.cv.domain.user.dto.login.PhoneDto;
import com.cv.domain.user.entity.User;

public interface UserServiceUtilsInterface {

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











