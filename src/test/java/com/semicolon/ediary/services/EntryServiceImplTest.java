package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.models.Entry;
import com.semicolon.ediary.repositories.EntryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EntryServiceImplTest {

    @Mock
    private EntryRepository entryRepository;

    @InjectMocks
    private EntryServiceImpl entryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testEntryObjectModel_canBeCreatedAndSaved() {
        //given
        Entry entry = new Entry();
        entry.setTitle("test-title");
        entry.setBody("test-body");
        entry.setLocalDateTime(LocalDateTime.now());

        CreateEntryRequestModel cERM = new CreateEntryRequestModel();
        cERM.setTitle("test-title");
        cERM.setBody("test-body");

        //when
        when(entryRepository.save(any())).thenReturn(entry);
        Entry savedEntry = entryService.createNewEntry(cERM);

        System.out.println(cERM);
        System.out.println(entry);
        System.out.println(savedEntry);

        //assert
        assertEquals(entry, savedEntry);
    }
}