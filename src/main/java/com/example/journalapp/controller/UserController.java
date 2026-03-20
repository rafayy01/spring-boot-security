package com.example.journalapp.controller;


import com.example.journalapp.entity.JournalEntity;
import com.example.journalapp.entity.User;
import com.example.journalapp.service.JournalService;
import com.example.journalapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    private List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping
    private ResponseEntity<User> register(@RequestBody User user){
        try{
            userService.register(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);        }
    }

    @PostMapping("/signin")
    private ResponseEntity<String> authentication(@RequestBody User user){
        try{
            String jwtToken = userService.authenticate(user);
            if(jwtToken != null){
                return new ResponseEntity<>(jwtToken,HttpStatus.FOUND);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);        }
    }

}
