package com.example.casecalendar.service.concretes;

import com.example.casecalendar.entitiy.User;
import com.example.casecalendar.repository.UserRepository;
import com.example.casecalendar.service.dtos.LoginRequest;
import com.example.casecalendar.service.dtos.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(RegisterRequest registerUserRequest) {
        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setEmail(registerUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        return userRepository.save(user);
    }

    public ResponseEntity<User> login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElse(null);

        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
