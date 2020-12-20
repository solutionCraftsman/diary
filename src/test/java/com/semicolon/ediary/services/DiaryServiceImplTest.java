package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.models.Diary;
import com.semicolon.ediary.models.Entry;
import com.semicolon.ediary.repositories.DiaryRepository;
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

class DiaryServiceImplTest {

    @Mock
    private DiaryRepository diaryRepository;

    @Mock
    private EntryService entryService;

    @InjectMocks
    private DiaryServiceImpl diaryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testEntry_canBeAdded() throws Exception{
        //given
        Diary diary = new Diary();
        Entry entry = new Entry();
        CreateEntryRequestModel cERM = new CreateEntryRequestModel();

        diary.setId("ayodele");
        diary.setLocalDateTime(LocalDateTime.now());
        diary.setNameOfOwner("ayo");

        entry.setTitle("test-title");
        entry.setBody("test-body");
        entry.setLocalDateTime(LocalDateTime.now());
        cERM.setTitle("test-title");
        cERM.setBody("test-body");

        when(diaryRepository.save(any())).thenReturn(diary);
        when(diaryRepository.findDiaryById("ayodele")).thenReturn(java.util.Optional.of(diary));
        when(entryService.saveEntryBeforeAddingToDiary(any())).thenReturn(entry);

        //when
        Diary savedDiary = diaryService.addNewEntry(cERM, "ayodele");

        assertEquals(diary, savedDiary);
        assertEquals(entry, savedDiary.getEntries().get(0));
    }
}

