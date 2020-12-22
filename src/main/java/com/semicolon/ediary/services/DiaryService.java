package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.models.Diary;
import com.semicolon.ediary.models.Entry;

import java.util.Optional;

public interface DiaryService {
    Diary createNewDiary(CreateDiaryRequestModel createDiaryRequestModel);
    Entry addNewEntry(CreateEntryRequestModel createEntryRequestModel, String diaryID) throws Exception;
    Optional<Diary> findDiaryById(String id);
}
