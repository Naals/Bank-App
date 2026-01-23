package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.configurations.JwtProvider;
import com.project.banksystemapp.payload.dto.UserDto;
import com.project.banksystemapp.payload.response.AuthResponse;
import com.project.banksystemapp.repository.UserRepository;
import com.project.banksystemapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomerUserImplementation customerUserImplementation;

    @Override
    public AuthResponse signUp(UserDto userDto) {
        return null;
    }

    @Override
    public AuthResponse login(UserDto userDto) {
        return null;
    }
}
