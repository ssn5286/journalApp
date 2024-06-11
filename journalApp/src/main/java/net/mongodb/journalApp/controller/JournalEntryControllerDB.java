package net.mongodb.journalApp.controller;




import net.mongodb.journalApp.entity.JournalEntryEntityDB;
import net.mongodb.journalApp.service.JournalEntryService;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerDB {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntryEntityDB> getAll(){
        return journalEntryService.findAllX();

    }

    @PostMapping
    public ResponseEntity<JournalEntryEntityDB> createEntry(@RequestBody JournalEntryEntityDB newEntry){
        try{
            newEntry.setDate(LocalDateTime.now());
            journalEntryService.entrySave(newEntry);
            return new ResponseEntity<>(newEntry,HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(newEntry,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public String updateIfExists(@RequestBody JournalEntryEntityDB newUEntry){

        return "Please enter and id at the end example: http://localhost:8080/journal/1";
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntryEntityDB> getEntry(@PathVariable ObjectId myId){
//        return journalEntryService.findById(myId).orElse(null);
        Optional<JournalEntryEntityDB> journalEntryEntityDB = journalEntryService.findById(myId);
       if (journalEntryEntityDB.isPresent()){
           return new ResponseEntity<>(journalEntryEntityDB.get(),HttpStatus.OK);
       }

        return new ResponseEntity<>(journalEntryEntityDB.get(),HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myId){
        JournalEntryEntityDB journalEntryEntityDB = journalEntryService.findById(myId).orElse(null);

        if (journalEntryEntityDB == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            journalEntryService.deleteId(journalEntryEntityDB);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/id/{uId}")
    public ResponseEntity<JournalEntryEntityDB> updateIfExists(@PathVariable ObjectId uId,@RequestBody JournalEntryEntityDB newUentry){
//        JournalEntryEntityDB oldEntry = journalEntryService.findById(uId).orElse(null);
        JournalEntryEntityDB oldEntry = journalEntryService.findById(uId).orElse(null);

        if (oldEntry !=null){
            oldEntry.setTitle(newUentry.getTitle() !=null && !newUentry.getTitle().equals("")? newUentry.getTitle(): oldEntry.getTitle());
            oldEntry.setContent(newUentry.getContent() !=null && !newUentry.getContent().equals("")?newUentry.getContent():oldEntry.getContent());
            journalEntryService.entrySave(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        else {
            newUentry.setDate(LocalDateTime.now());
            journalEntryService.entrySave(newUentry);
            return new ResponseEntity<>(oldEntry,HttpStatus.NOT_FOUND);
        }

    }

}
