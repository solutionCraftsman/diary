package com.semicolon.ediary.repositories;

import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.models.Diary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class DiaryRepositoryTest {

    @Autowired
    private DiaryRepository diaryRepository;

    //given
    private Diary diary = new Diary();;

    @BeforeEach
    void setUp() {
        //..given
        diary.setNameOfOwner("ayo");
        //diary.setLocalDateTime(LocalDateTime.now());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSave() {
        //when
        Diary savedDiary = diaryRepository.save(diary);

        assertNotNull(savedDiary.getId());
        assertEquals(diary, savedDiary);
        System.out.println(diary);
        System.out.println(savedDiary);
    }

    @Test
    void testFindDiaryByID() {
        //when
        Diary savedDiary = diaryRepository.save(diary);
        Optional<Diary> foundDiary = diaryRepository.findDiaryById(savedDiary.getId());
        assertEquals(savedDiary, foundDiary.orElse(null));
    }
}