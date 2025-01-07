package com.example.jfsd.service;

import com.example.jfsd.model.User;
import com.example.jfsd.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public boolean validateUser(String username, String password) {
        User user = findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
