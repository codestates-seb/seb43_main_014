package com.cv.domain.user.mapper;

import com.cv.domain.user.dto.UserDto;
import com.cv.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto.SignUpResponse userPostToSignUpResponse(User user);
    User userPostDtoToUser(UserDto.Post userPostDto);
    User userPasswordPatchDtoToUser(UserDto.PasswordPatch userPasswordPatchDto);
    User userPasswordPostDtoToUser(UserDto.PasswordPost userPasswordPostDto);

    User userPatchDtoToUser(UserDto.Patch userPatchDto);

    UserDto.UserPatchResponse userPatchToResponse(User updatedUser);
}

