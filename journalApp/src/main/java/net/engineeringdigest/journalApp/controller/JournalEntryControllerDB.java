package net.engineeringdigest.journalApp.controller;




import net.engineeringdigest.journalApp.entity.JournalEntryEntityDB;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerDB {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntryEntityDB> getAll(){
        return journalEntryService.findAll();

    }

    @PostMapping
    public String createEntry(@RequestBody JournalEntryEntityDB newEntry){
        newEntry.setDate(LocalDateTime.now());
        journalEntryService.entrySave(newEntry);
        return "post Ok";
    }

    @PutMapping
    public String updateIfExists(@RequestBody JournalEntryEntityDB newUEntry){

        return "Please enter and id at the end example: http://localhost:8080/journal/1";
    }

    @GetMapping("id/{myId}")
    public JournalEntryEntityDB getEntry(@PathVariable ObjectId myId){

        return journalEntryService.findById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public String deleteEntry(@PathVariable ObjectId myId){
        JournalEntryEntityDB journalEntryEntityDB = journalEntryService.findById(myId).orElse(null);

        if (journalEntryEntityDB == null){
            return "Id does not exist in database";
        }
        else {
            journalEntryService.deleteId(journalEntryEntityDB);
            return "Id deleted successfully";
        }
    }

    @PutMapping("/id/{uId}")
    public JournalEntryEntityDB updateIfExists(@PathVariable ObjectId uId,@RequestBody JournalEntryEntityDB newUentry){
        JournalEntryEntityDB oldEntry = journalEntryService.findById(uId).orElse(null);
        if (oldEntry !=null){
            oldEntry.setTitle(newUentry.getTitle() !=null && !newUentry.getTitle().equals("")? newUentry.getTitle(): oldEntry.getTitle());
            oldEntry.setContent(newUentry.getContent() !=null && !newUentry.getContent().equals("")?newUentry.getContent():oldEntry.getContent());
            journalEntryService.entrySave(oldEntry);
            return oldEntry;
        }
        else {
            newUentry.setDate(LocalDateTime.now());
            journalEntryService.entrySave(newUentry);
            return newUentry;
        }

    }

}
