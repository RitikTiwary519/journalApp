package net.engineeringdigest.journalApp.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document("journal_entries")
public class JournalEntry {
    @Id
    public ObjectId id;
    public String title;
    public String content;
    public LocalDate created;

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    public LocalDate modified;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
