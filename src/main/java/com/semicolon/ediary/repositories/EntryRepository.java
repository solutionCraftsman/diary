package com.semicolon.ediary.repositories;

import com.semicolon.ediary.models.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntryRepository extends MongoRepository<Entry, String> {
    Optional<Entry> findEntryByTitle(String title);
}
