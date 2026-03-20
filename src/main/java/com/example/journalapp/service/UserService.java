package com.example.journalapp.service;

import com.example.journalapp.entity.User;
import com.example.journalapp.jwt.JwtService;
import com.example.journalapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


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

    public String authenticate(User user) {
        Authentication authentication =  authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if(authentication.isAuthenticated()){
                return jwtService.generateToken(user.getUsername());
            }
            return null;
    }

    public List<User> getAllUsers (){
        return userRepository.findAll();
    }
}
