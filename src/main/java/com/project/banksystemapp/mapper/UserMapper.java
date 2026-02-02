package com.project.banksystemapp.mapper;

import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.UserDto;


public final class UserMapper {

    private UserMapper() {}

    public static UserDto toDto(final User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setFullName(user.getFullName());
        userDto.setPhone(user.getPhone());
        userDto.setLastLogin(user.getLastLogin());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        return userDto;
    }

    public static User toEntity(final UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setFullName(userDto.getFullName());
        user.setPhone(userDto.getPhone());
        user.setLastLogin(userDto.getLastLogin());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        user.setPassword(userDto.getPassword());
        return user;
    }

}
