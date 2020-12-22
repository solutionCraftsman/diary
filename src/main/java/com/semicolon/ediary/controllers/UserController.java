package com.semicolon.ediary.controllers;

import com.semicolon.ediary.dtos.ApiResponse;
import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.dtos.CreateUserRequestModel;
import com.semicolon.ediary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("create")
    public ResponseEntity<?> createNewUser(@RequestBody CreateUserRequestModel createUserRequestModel) {
        try {
            userService.createNewUser(createUserRequestModel);
            return new ResponseEntity<>(new ApiResponse(true, "User created Successfully"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{userID}/new-diary")
    public ResponseEntity<?> createNewDiary(@RequestBody CreateDiaryRequestModel createDiaryRequestModel, @PathVariable String userID) {
        try {
            userService.addNewDiary(createDiaryRequestModel, userID);
            return new ResponseEntity<>(new ApiResponse(true, "Diary created Successfully"), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{userID}/{diaryID}/new-diary-entry")
    public ResponseEntity<?> createNewDiaryEntry(@RequestBody CreateEntryRequestModel createEntryRequestModel, @PathVariable String userID, @PathVariable String diaryID) {
        try {
            userService.addNewDiaryEntry(createEntryRequestModel, userID, diaryID);
            return new ResponseEntity<>(new ApiResponse(true, "Diary created Successfully"), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
