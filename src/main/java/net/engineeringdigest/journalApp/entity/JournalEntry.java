package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Data
@Document("journal_entries")
@NoArgsConstructor
public class JournalEntry {
    @Id
    public ObjectId id;
    @NonNull
    public String title;
    public String content;
    public LocalDate created;
    public LocalDate modified;
}
