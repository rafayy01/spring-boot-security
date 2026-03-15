package com.example.journalapp.service;

import com.example.journalapp.entity.JournalEntity;
import com.example.journalapp.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public void saveEntry(JournalEntity journalEntity){
        journalRepository.save(journalEntity);
    }

    public Optional<JournalEntity> getById(ObjectId Id){
        Boolean isPresent = journalRepository.existsById(Id);
        if(isPresent){
            return journalRepository.findById(Id);
        }else{
           return null;
        }
    }

    public List<JournalEntity> findAllJournalEntities (){
        return journalRepository.findAll();
    }

    public Boolean deleteById (ObjectId id){
        journalRepository.deleteById(id);
        return true;
    }
}
