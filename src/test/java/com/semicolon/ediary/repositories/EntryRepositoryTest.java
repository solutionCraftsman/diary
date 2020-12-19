package com.semicolon.ediary.repositories;

import com.semicolon.ediary.models.Entry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class EntryRepositoryTest {

    @Autowired
    private EntryRepository entryRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSave() {
        //given
        Entry entry = new Entry();
        entry.setTitle("test-title");
        entry.setBody("test-body");
        entry.setLocalDateTime(LocalDateTime.now());

        //when
        Entry savedEntry = entryRepository.save(entry);

        //assert
        assertNotNull(entry.getId());
        assertEquals(entry, savedEntry);
    }
}