package com.project.banksystemapp.service;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.payload.dto.UserDto;
import com.project.banksystemapp.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signUp(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto) throws UserException;
}
