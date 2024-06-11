package net.mongodb.journalApp.service;

import net.mongodb.journalApp.entity.JournalEntryEntityDB;
import net.mongodb.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
//Detectable by IOC
public class JournalEntryService {
    @Autowired
    //Indicates IOC this is eligible
    private JournalEntryRepository journalEntryRepository;

    //Using the save method from MongoDB interface
    public void entrySave(JournalEntryEntityDB journalEntryEntityDB){
        journalEntryRepository.save(journalEntryEntityDB);
    }

    //Using the findAll method from MongoDB interface
    public List<JournalEntryEntityDB> findAllX(){
        return journalEntryRepository.findAll();

    }

    //Using the findById from MongoDB interface
    public Optional<JournalEntryEntityDB> findById(ObjectId objectId){

        return journalEntryRepository.findById(objectId);
    }

    public void deleteId(JournalEntryEntityDB journalEntryEntityDB){
        journalEntryRepository.delete(journalEntryEntityDB);
    }


}
