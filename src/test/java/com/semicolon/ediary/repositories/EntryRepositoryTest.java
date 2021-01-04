package com.semicolon.ediary.repositories;

import com.semicolon.ediary.models.Entry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class EntryRepositoryTest {

    //given
    private Entry entry = new Entry();

    @Autowired
    private EntryRepository entryRepository;

    @BeforeEach
    void setUp() {
        //..given
        entry.setTitle("test-title");
        entry.setBody("test-body");
        //tail part of time gets stripped off after save
        //entry.setLocalDateTime(LocalDateTime.now());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSave() {
        //when
        Entry savedEntry = entryRepository.save(entry);

        //assert
        assertNotNull(entry.getId());
        assertEquals(entry, savedEntry);
    }

    @Test
    void testFindEntryByID() {
        //..given
        Entry savedEntry = entryRepository.save(entry);

        //when
        Optional<Entry> foundEntry = entryRepository.findEntryById(savedEntry.getId());
        //assert
        assertEquals(savedEntry, foundEntry.orElse(null));
    }
}

