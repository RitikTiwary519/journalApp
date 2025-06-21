package net.engineeringdigest.journalApp.controller;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<?> getUserEntries() {
        List<User> val = userService.getUserEntries();
        if(val.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(val, HttpStatus.OK);
        }
    }


    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User myEntry) {
        try{
            userService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("So there was an error during the posting please bearing with us while we look to fix it", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//
//    @GetMapping("id/{myId}")
//    public ResponseEntity<?> getEntryById(@PathVariable ObjectId myId) {
//        User val = User.getUser(myId);
//        if(val==null)
//        {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        else
//        {
//            return new ResponseEntity<>(val, HttpStatus.OK);
//        }
//    }

//    @DeleteMapping("id/{myId}")
//    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId) {
//        JournalEntry val = journalEntryService.getJournalEntryByID(myId);
//
//        if(val==null)
//        {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        else
//        {
//            journalEntryService.deleteJournalEntry(myId);
//            return new ResponseEntity<>("Deleted of the ID " + myId +" from the journal entry is successful", HttpStatus.GONE);
//
//        }
//    }
//    @PutMapping("id/{myId}")
//    public ResponseEntity<String> updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry) {
//        JournalEntry oldEntry = journalEntryService.getJournalEntryByID(myId);
//
//        if (oldEntry == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Journal entry not found");
//        }
//
//        boolean updated = false;
//
//        if (myEntry.getContent() != null && !myEntry.getContent().isEmpty()) {
//            oldEntry.setContent(myEntry.getContent());
//            updated = true;
//        }
//
//        if (myEntry.getTitle() != null && !myEntry.getTitle().isEmpty()) {
//            oldEntry.setTitle(myEntry.getTitle());
//            updated = true;
//        }
//
//        if (updated) {
//            oldEntry.setModified(LocalDate.now());
//            return ResponseEntity.ok("Journal entry updated successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No valid fields to update");
//        }
//    }
@PutMapping("/{id}")
public ResponseEntity<?> updateEntryById(@PathVariable ObjectId id, @RequestBody User myEntry) {
    User founded = userService.getUserByID(id);

    if (founded == null) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("bruh you just gave me a wrong id:\n");
    }
    else if (myEntry.getUserName() != null) {
        founded.setUserName(myEntry.getUserName());
    }
    else if (myEntry.getPassword() != null) {
        founded.setPassword(myEntry.getPassword());
    }

    userService.saveEntry(founded);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("Job done guru");
}
}

