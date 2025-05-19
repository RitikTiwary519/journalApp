package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface journalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}

//mongo repo is already presented interface that performs the basic crud opreration
// controller ---> service ---> repository (which uses interface)