package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.configurations.JwtProvider;
import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.repository.UserRepository;
import com.project.banksystemapp.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;


    @Override
    public User getUserFromJwtToken(String token) throws UserException {
        String email = jwtProvider.getEmailFromToken(token);

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("Invalid jwt token");
        }

        return user;
    }


    @Override
    public User getCurrentUser() throws UserException {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserException("Invalid user");
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserException("Invalid user");
        }

        return user;
    }

    @Override
    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
