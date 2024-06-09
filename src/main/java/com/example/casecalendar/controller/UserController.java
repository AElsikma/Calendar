package com.example.casecalendar.controller;

import com.example.casecalendar.entitiy.User;
import com.example.casecalendar.service.concretes.UserService;
import com.example.casecalendar.service.dtos.LoginRequest;
import com.example.casecalendar.service.dtos.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest registerUserRequest) {
        User user = userService.registerUser(registerUserRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}