package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.dtos.CreateUserRequestModel;
import com.semicolon.ediary.models.Diary;
import com.semicolon.ediary.models.Entry;
import com.semicolon.ediary.models.User;

public interface UserService {
    User createNewUser(CreateUserRequestModel createUserRequestModel) throws Exception;
    Diary addNewDiary(CreateDiaryRequestModel createDiaryRequestModel, String userID) throws Exception;
    Entry addNewDiaryEntry(CreateEntryRequestModel createEntryRequestModel, String userID, String diaryID) throws Exception;
}
