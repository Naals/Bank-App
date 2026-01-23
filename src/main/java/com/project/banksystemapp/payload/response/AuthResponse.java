package com.project.banksystemapp.payload.response;

import com.project.banksystemapp.payload.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private UserDto user;
}
