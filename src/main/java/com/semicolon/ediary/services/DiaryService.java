package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.models.Diary;

public interface DiaryService {
    Diary createNewDiary(CreateDiaryRequestModel createDiaryRequestModel);
    Diary addNewEntry(CreateEntryRequestModel createEntryRequestModel, String diaryId) throws Exception;
}
