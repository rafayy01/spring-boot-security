package com.example.journalapp.controller;


import com.example.journalapp.entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalController {
    private Map<String, JournalEntity> journalEntityHashMap = new HashMap<String, JournalEntity>();

    @GetMapping
    private List<JournalEntity> getAll(){
        return new ArrayList<>(journalEntityHashMap.values());
    }
    @PostMapping
    private Boolean addEntry(@RequestBody JournalEntity journal){
//        journalEntityHashMap.put(journal.getId(), journal);
        return true;
    }
    @GetMapping("id/{myId}")
    private JournalEntity getById(@PathVariable Long myId){
        return journalEntityHashMap.get(myId);
    }

    @PutMapping("/id/{myId}")
    private JournalEntity updateJournal(@PathVariable String myId,@RequestBody JournalEntity journalEntity){
        journalEntityHashMap.put(myId,journalEntity);
        return journalEntity;
    }

    @DeleteMapping("/id/{myId}")
    private Boolean deleteById(@PathVariable Long myId){
        journalEntityHashMap.remove(myId);
        return true;
    }
}
