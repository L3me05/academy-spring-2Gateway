package com.example.academyspring2gateway.controller;

import com.example.academyspring2gateway.model.Users;
import com.example.academyspring2gateway.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Users users) {
        if(userRepository.existsByUsername(users.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        if(users.getRole() == null) users.setRole("USER");
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepository.save(users);

        return ResponseEntity.ok("User created successfully");
    }


}
