package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class journalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getJournalEntries() {
        List<JournalEntry> val = journalEntryService.getJournalEntries();
        if (val.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(val, HttpStatus.OK);
        }
    }

    @GetMapping("/listofID")
    public ResponseEntity<?> getJournalEntriesID() {
        List<JournalEntry> val = journalEntryService.getJournalEntries();
        if (val.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<ObjectId> ids = new ArrayList<>();
            for (JournalEntry entry : val) {
                ids.add(entry.getId());
            }
            return new ResponseEntity<>(ids, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> addJournalEntry(@RequestBody JournalEntry myEntry) {
        try {
            myEntry.setCreated(LocalDate.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Optional: log to logger
            return new ResponseEntity<>(
                    "There was an error while posting your journal entry. Please bear with us as we work to fix it.",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getEntryById(@PathVariable ObjectId myId) {
        JournalEntry val = journalEntryService.getJournalEntryByID(myId);
        if (val == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(val, HttpStatus.OK);
        }
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId) {
        JournalEntry val = journalEntryService.getJournalEntryByID(myId);
        if (val == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            journalEntryService.deleteJournalEntry(myId);
            return new ResponseEntity<>(
                    "Successfully deleted journal entry with ID: " + myId,
                    HttpStatus.GONE
            );
        }
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<String> updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry) {
        JournalEntry oldEntry = journalEntryService.getJournalEntryByID(myId);

        if (oldEntry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Journal entry not found");
        }

        boolean updated = false;

        if (myEntry.getContent() != null && !myEntry.getContent().isEmpty()) {
            oldEntry.setContent(myEntry.getContent());
            updated = true;
        }

        if (myEntry.getTitle() != null && !myEntry.getTitle().isEmpty()) {
            oldEntry.setTitle(myEntry.getTitle());
            updated = true;
        }

        if (updated) {
            oldEntry.setModified(LocalDate.now());
            journalEntryService.saveEntry(oldEntry); // ✅ Save after update
            return ResponseEntity.ok("Journal entry updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No valid fields to update");
        }
    }
}
