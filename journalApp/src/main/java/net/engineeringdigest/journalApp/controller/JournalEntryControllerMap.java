package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntryMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import java.util.HashMap;

@RestController
@RequestMapping("/_journal")
public class JournalEntryControllerMap {

    private Map<Long, JournalEntryMap> journalEntriesMap = new HashMap<>();


    @GetMapping
    public List<JournalEntryMap> getAll(){
        return new ArrayList<>(journalEntriesMap.values());

    }

    @PostMapping
    public String createEntry(@RequestBody JournalEntryMap newEntry){

        journalEntriesMap.put(newEntry.getId(),newEntry);
        return "post Ok";
    }

    @PutMapping
    public String updateIfExists(@RequestBody JournalEntryMap newUEntry){
        journalEntriesMap.put(newUEntry.getId(),newUEntry);
        return "put Ok";
    }

    @GetMapping("id/{myId}")
    public JournalEntryMap getEntry(@PathVariable Long myId){
        if (journalEntriesMap.containsKey(myId)) {
            return journalEntriesMap.get(myId);
        }
        return new JournalEntryMap(0,null,null);
    }

    @DeleteMapping("/id/{myId}")
    public String deleteEntry(@PathVariable Long myId){
        if (journalEntriesMap.containsKey(myId)){
            journalEntriesMap.remove(myId);
            return "Delete Success";
        }
        return "Key not found";
    }
}
