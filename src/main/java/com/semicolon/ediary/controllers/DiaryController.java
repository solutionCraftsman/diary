package com.semicolon.ediary.controllers;

import com.semicolon.ediary.dtos.ApiResponse;
import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.services.DiaryService;
import com.semicolon.ediary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
@RequestMapping("/diary/")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private UserService userService;

    @PostMapping("create")
    public ResponseEntity<?> createNewDiary(@RequestBody CreateDiaryRequestModel createDiaryRequestModel) {
        diaryService.createNewDiary(createDiaryRequestModel);
        return new ResponseEntity<>(new ApiResponse(true, "New Diary Created"), HttpStatus.CREATED);
    }

    @PostMapping("{diaryID}/addNewEntry")
    //public ResponseEntity<?> addNewEntry(@RequestBody CreateEntryRequestModel createEntryRequestModel, @PathVariable String diaryId) {
    public ResponseEntity<?> createNewEntry(@RequestBody CreateEntryRequestModel createEntryRequestModel, @PathVariable String diaryID) {
        try {
            diaryService.createNewEntry(createEntryRequestModel, diaryID);
            return new ResponseEntity<>(new ApiResponse(true, "Entry Added to Diary"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
