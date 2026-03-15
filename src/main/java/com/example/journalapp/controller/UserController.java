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

//    @Autowired
//    private JournalService journalService;
    @Autowired
    private UserService userService;

//    @GetMapping
//    private List<JournalEntity> getAll(){
//        return journalService.findAllJournalEntities();
//    }
    @PostMapping
    private ResponseEntity<User> register(@RequestBody User user){
        try{
            userService.register(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);        }
    }
//    @GetMapping("id/{myId}")
//    private ResponseEntity<JournalEntity> getById(@PathVariable ObjectId myId){
//        Optional<JournalEntity> journal = journalService.getById(myId);
//        if(journal != null && journal.isPresent()){
//            return new ResponseEntity<>(journal.get(),HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>(journal.get(),HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PutMapping("/id/{myId}")
//    private JournalEntity updateJournal(@PathVariable ObjectId myId,@RequestBody JournalEntity journalEntity){
//        JournalEntity old = journalService.getById(myId).orElse(null);
//        if(old != null){
//            old.setTitle(journalEntity.getTitle());
//            old.setContent(journalEntity.getContent());
//        }
//        journalService.saveEntry(old);
//        return old;
//    }
//
//    @DeleteMapping("/id/{myId}")
//    private Boolean deleteById(@PathVariable ObjectId myId){
//        return journalService.deleteById(myId);
//    }
}
