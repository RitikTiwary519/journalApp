package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.journalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class JournalEntryService {
    @Autowired
     private journalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry entry) {
        journalEntryRepository.save(entry);
    }

    public List<JournalEntry> getJournalEntries() {
        return journalEntryRepository.findAll();
    }

    public void deleteJournalEntry(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }

    public JournalEntry getJournalEntryByID(ObjectId id) {
        return journalEntryRepository.findById(id).get();
    }
}
