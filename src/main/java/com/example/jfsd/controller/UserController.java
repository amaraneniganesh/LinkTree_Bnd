package com.example.jfsd.controller;

import com.example.jfsd.model.User;
import com.example.jfsd.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists.");
        }
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if (userService.validateUser(username, password)) {
            return ResponseEntity.ok("Login successful.");
        }
        return ResponseEntity.badRequest().body("Invalid username or password.");
    }
}
