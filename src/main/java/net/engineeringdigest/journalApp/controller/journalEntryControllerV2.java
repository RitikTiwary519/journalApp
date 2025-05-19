package net.engineeringdigest.journalApp.controller;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class journalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping
    public List<JournalEntry> getJournalEntries() {
        return journalEntryService.getJournalEntries();
    }

    @PostMapping
    public boolean addJournalEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setCreated(LocalDate.now());
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getEntryById(@PathVariable ObjectId myId) {
        return journalEntryService.getJournalEntryByID(myId);
    }

    @DeleteMapping("id/{myId}")
    public String deleteEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteJournalEntry(myId);
        return "Deleted of the ID " + myId +" from the journal entry is successful";
    }

    @PutMapping("id/{myId}")
    public boolean updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry) {
        JournalEntry oldEntry = journalEntryService.getJournalEntryByID(myId);
        if(oldEntry!=null)
        {
            if(myEntry.content!=null && !myEntry.content.isEmpty())
            {
                oldEntry.setTitle(myEntry.content );
            }
            if(myEntry.title!=null && !myEntry.title.isEmpty())
            {
                oldEntry.setTitle(myEntry.title);
            }
            oldEntry.setModified(LocalDate.now());
        }

        return true;
    }
}
