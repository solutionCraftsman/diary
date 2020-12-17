package com.semicolon.ediary.controllers;

import com.semicolon.ediary.dtos.ApiResponse;
import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.services.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diary/")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @PostMapping("create")
    public ResponseEntity<?> createNewDiary(@RequestBody CreateDiaryRequestModel createDiaryRequestModel) {
        diaryService.createNewDiary(createDiaryRequestModel);
        return new ResponseEntity<>(new ApiResponse(true, "New Diary Created"), HttpStatus.CREATED);
    }

    @PostMapping("addNewEntry/{diaryId}")
    public ResponseEntity<?> addNewEntry(@RequestBody CreateEntryRequestModel createEntryRequestModel, @PathVariable String diaryId) {
        try {
            diaryService.addNewEntry(createEntryRequestModel, diaryId);
            return new ResponseEntity<>(new ApiResponse(true, "Entry Added to Diary"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
