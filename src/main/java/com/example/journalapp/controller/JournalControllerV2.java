package com.example.journalapp.controller;


import com.example.journalapp.entity.JournalEntity;
import com.example.journalapp.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journalV2")
public class JournalControllerV2 {

    @Autowired
    private JournalService journalService;

    @GetMapping
    private List<JournalEntity> getAll(){
        return journalService.findAllJournalEntities();
    }
    @PostMapping
    private ResponseEntity<JournalEntity> addEntry(@RequestBody JournalEntity journal){
        try{
            journalService.saveEntry(journal);
            return new ResponseEntity<>(journal,HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);        }
    }
    @GetMapping("id/{myId}")
    private ResponseEntity<JournalEntity> getById(@PathVariable ObjectId myId){
        Optional<JournalEntity> journal = journalService.getById(myId);
        if(journal != null && journal.isPresent()){
            return new ResponseEntity<>(journal.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(journal.get(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/id/{myId}")
    private JournalEntity updateJournal(@PathVariable ObjectId myId,@RequestBody JournalEntity journalEntity){
        JournalEntity old = journalService.getById(myId).orElse(null);
        if(old != null){
            old.setTitle(journalEntity.getTitle());
            old.setContent(journalEntity.getContent());
        }
        journalService.saveEntry(old);
        return old;
    }

    @DeleteMapping("/id/{myId}")
    private Boolean deleteById(@PathVariable ObjectId myId){
        return journalService.deleteById(myId);
    }
}
