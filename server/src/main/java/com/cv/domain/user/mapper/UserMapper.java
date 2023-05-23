package com.cv.domain.user.mapper;

import com.cv.domain.user.dto.mypage.UserPasswordPatchDto;
import com.cv.domain.user.dto.mypage.UserPatchDto;
import com.cv.domain.user.dto.mypage.UserPatchResponseDto;
import com.cv.domain.user.dto.sign.SignUpResponseDto;
import com.cv.domain.user.dto.sign.UserPostDto;
import com.cv.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    SignUpResponseDto userPostToSignUpResponse(User user);
    User userPostDtoToUser(UserPostDto userPostDto);
    UserPatchResponseDto userPatchToResponse(User updatedUser);
}

