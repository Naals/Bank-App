package com.project.banksystemapp.service;

import com.project.banksystemapp.payload.dto.UserDto;
import com.project.banksystemapp.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signUp(UserDto userDto);
    AuthResponse login(UserDto userDto);
}
