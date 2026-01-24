package com.project.banksystemapp.service;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.modal.User;

import java.util.List;

public interface UserService {

    User getUserFromJwtToken(String token) throws UserException;
    User getCurrentUser() throws UserException;
    User getUserByEmail(String email) throws UserException;
    User getUserById(Long id);
    List<User> getAllUsers();
}
