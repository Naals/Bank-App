package com.project.banksystemapp.controller;


import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.mapper.UserMapper;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.UserDto;
import com.project.banksystemapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(UserMapper.toUserDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    )  {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toUserDto(user));
    }
}
