package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.journalEntryRepository;
import net.engineeringdigest.journalApp.repository.userRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserService {
    @Autowired
    private userRepository URepo;

    public void saveEntry(User entry) {

        URepo.save(entry);
        System.out.println("Saved this "+entry);
    }

    public List<User> getUserEntries() {
        return URepo.findAll();
    }

    public void deleteUser(ObjectId id) {
        URepo.deleteById(id);
    }

    public User getUserByID(ObjectId id) {
        return URepo.findById(id).get();
    }
}
