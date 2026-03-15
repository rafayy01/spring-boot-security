package com.example.journalapp.service;

import com.example.journalapp.entity.User;
import com.example.journalapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {

        String encodedPassword =
                passwordEncoder.encode(user.getPassword());
        user.setUsername(user.getUsername());
        user.setRole(user.getRole());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }
}
