package com.project.banksystemapp.mapper;

import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.UserDto;


public class UserMapper {

    private UserMapper() {}

    public static UserDto toUserDto(final User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setFullName(user.getFullName());
        userDto.setPhone(user.getPhone());
        userDto.setLastLogin(user.getLastLogin());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        return userDto;
    }

}
