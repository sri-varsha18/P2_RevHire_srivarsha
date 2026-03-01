package com.rev.app.revhire.mapper;

import com.rev.app.revhire.dto.UserDto;
import com.rev.app.revhire.entity.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        if (user == null)
            return null;
        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static User toEntity(UserDto dto) {
        if (dto == null)
            return null;
        return User.builder()
                .userId(dto.getUserId())
                .email(dto.getEmail())
                .role(dto.getRole())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}
