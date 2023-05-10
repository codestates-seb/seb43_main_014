package com.cv.domain.user.mapper;

import com.cv.domain.user.dto.UserDto;
import com.cv.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User userPostDtoToUser(UserDto.Post userPostDto);

    UserDto.SignUpResponse userPostToSignUpResponse(User user);
}

