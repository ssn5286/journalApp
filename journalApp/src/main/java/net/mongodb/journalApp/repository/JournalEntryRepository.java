package net.mongodb.journalApp.repository;

import net.mongodb.journalApp.entity.JournalEntryEntityDB;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//Indicates this is repository

public interface JournalEntryRepository extends MongoRepository<JournalEntryEntityDB, ObjectId> {

}
