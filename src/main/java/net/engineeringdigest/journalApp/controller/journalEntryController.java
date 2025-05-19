package net.engineeringdigest.journalApp.controller;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class journalEntryController {
    private final ParameterNamesModule parameterNamesModule;
    private Map<ObjectId, JournalEntry> journalEntries = new HashMap<>();

    public journalEntryController(ParameterNamesModule parameterNamesModule) {
        this.parameterNamesModule = parameterNamesModule;
    }

    @GetMapping
    public List<JournalEntry> getJournalEntries() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean addJournalEntry(@RequestBody JournalEntry myEntry) {
        journalEntries.put(myEntry.getId(),myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getEntryById(@PathVariable long myId) {
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable long myId) {
        journalEntries.remove(myId);
        return true;
    }



}
