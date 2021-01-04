package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.models.Diary;
import com.semicolon.ediary.models.Entry;
import com.semicolon.ediary.models.User;
import com.semicolon.ediary.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DiaryService diaryService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testDiary_canBeAdded() throws Exception {
        Diary diary = new Diary();
        User user = new User();

        CreateDiaryRequestModel createDiaryRequestModel = new CreateDiaryRequestModel();
        createDiaryRequestModel.setNameOfOwner("testOwner");

        user.setId("testID");
        user.setUsername("testUsername");
        user.setEmail("test@email.com");
        user.setPassword("testP@ssword");

        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findUserById("testID")).thenReturn(java.util.Optional.of(user));
        when(diaryService.createNewDiary(any())).thenReturn(diary);

        Diary savedDiary = userService.createNewDiary(createDiaryRequestModel, user.getId());

        assertEquals(diary, savedDiary);
    }

    @Test
    void testEntry_canBeAdded() throws Exception {
        Entry entry = new Entry();
        Diary diary = new Diary();
        User user = new User();

        CreateEntryRequestModel createEntryRequestModel = new CreateEntryRequestModel();
        createEntryRequestModel.setTitle("testTitle");
        createEntryRequestModel.setBody("testBody");

        entry.setTitle("testTitle");
        entry.setBody("testBody");
        entry.setLocalDateTime(LocalDateTime.now());

        diary.setId("testID");
        diary.setNameOfOwner("testOwner");
        diary.setLocalDateTime(LocalDateTime.now());

        user.setId("testID");
        user.setUsername("testUsername");
        user.setEmail("test@email.com");
        user.setPassword("testP@ssword");
        user.getDiaries().add(diary);

        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findUserById("testID")).thenReturn(java.util.Optional.of(user));
        when(diaryService.findDiaryById("testID")).thenReturn(diary);
        when(diaryService.createNewEntry(any(), eq(diary.getId()))).thenReturn(entry);

        Entry savedEntry = userService.createNewDiaryEntry(createEntryRequestModel, diary.getId(), user.getId());

        assertEquals(entry, savedEntry);
    }
}