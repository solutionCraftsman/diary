package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.dtos.CreateUserRequestModel;
import com.semicolon.ediary.models.Diary;
import com.semicolon.ediary.models.Entry;
import com.semicolon.ediary.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createNewUser(CreateUserRequestModel createUserRequestModel) throws Exception;
    Diary createNewDiary(CreateDiaryRequestModel createDiaryRequestModel, String userID) throws Exception;
    Entry createNewDiaryEntry(CreateEntryRequestModel createEntryRequestModel, String userID, String diaryID) throws Exception;

    List<Diary> getAllDiaries(String userID) throws Exception;

    Diary getDiary(String userID, String diaryID) throws Exception;

    List<Entry> getAllEntries(String userID, String diaryID) throws Exception;

    Entry getEntry(String userID, String diaryID, String entryID) throws Exception;

    List<User> getAllUsers();
}
