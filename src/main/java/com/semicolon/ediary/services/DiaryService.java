package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.models.Diary;
import com.semicolon.ediary.models.Entry;

import java.util.List;
import java.util.Optional;

public interface DiaryService {
    Diary createNewDiary(CreateDiaryRequestModel createDiaryRequestModel);
    Entry createNewEntry(CreateEntryRequestModel createEntryRequestModel, String diaryID) throws Exception;
    Diary findDiaryById(String id) throws Exception;

    List<Entry> getAllEntries(String diaryID) throws Exception;

    Entry getEntry(String diaryID, String entryID) throws Exception;
}
