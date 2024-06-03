package com.example.wsearch.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.wsearch.dto.Credentials;
import com.example.wsearch.dto.JwtToken;
import com.example.wsearch.dto.UserDto;
import com.example.wsearch.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public void signUp(@RequestBody UserDto userDto) {
        userService.signUp(userDto);
    }

    @PostMapping("/login")
    public DecodedJWT signIn(@RequestBody Credentials credentials) throws AccessDeniedException {
        return userService.signIn(credentials.getEmail(), credentials.getPassword());
    }
}