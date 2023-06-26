package com.cv.domain.user.service;

import com.cv.domain.user.dto.mypage.ProfileImageDto;
import com.cv.domain.user.dto.mypage.UserPasswordPatchDto;
import com.cv.domain.user.dto.mypage.UserPatchDto;
import com.cv.domain.user.dto.mypage.UserPatchResponseDto;

import java.time.LocalDate;

public interface UserInfoServiceInterface {
    /**
     * <h2>비밀번호를 변경한다.</h2>
     * </br>
     * @param uuid 일련의 숫자와 문자로 구성된 128비트 랜덤 문자열
     * @param userPasswordPatchDto 현재 비밀번호, 새 비밀번호
     * @return 비밀번호 최신 업데이트 날짜
     */
    LocalDate changePassword(String uuid, UserPasswordPatchDto userPasswordPatchDto);

    /**
     * <h2>user의 기본정보를 업데이트 한다.</h2>
     * </br>
     * @param uuid 일련의 숫자와 문자로 구성된 128비트 랜덤 문자열
     * @param userInfoPatchDto name, phone
     * @return name, email, phone, profileImage, 마지막 수정일자, 회원가입 일자
     */
    UserPatchResponseDto updateUserInfo(String uuid, UserPatchDto userInfoPatchDto);

    /**
     * <h2>회원의 프로필사진을 업로드한다.</h2>
     * </br>
     * @param uuid 일련의 숫자와 문자로 구성된 128비트 랜덤 문자열
     * @param profileImageDto profileImage경로를 저장
     * @return name, email, phone, profileImage, 마지막 수정일자, 회원가입 일자
     */
    UserPatchResponseDto uploadProfile(String uuid, ProfileImageDto profileImageDto);


}
