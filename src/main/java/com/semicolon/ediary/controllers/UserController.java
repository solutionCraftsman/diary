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
            userService.createNewDiary(createDiaryRequestModel, userID);
            return new ResponseEntity<>(new ApiResponse(true, "Diary created Successfully"), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{userID}/{diaryID}/new-diary-entry")
    public ResponseEntity<?> createNewDiaryEntry(@RequestBody CreateEntryRequestModel createEntryRequestModel, @PathVariable String userID, @PathVariable String diaryID) {
        try {
            userService.createNewDiaryEntry(createEntryRequestModel, userID, diaryID);
            return new ResponseEntity<>(new ApiResponse(true, "Diary created Successfully"), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("all-users")
    public ResponseEntity<?> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{userID}/all-diaries")
    public ResponseEntity<?> getAllDiaries(@PathVariable String userID) {
        try {
            return new ResponseEntity<>(userService.getAllDiaries(userID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{userID}/diary/{diaryID}")
    public ResponseEntity<?> getDiary(@PathVariable String userID, @PathVariable String diaryID) {
        try {
            return new ResponseEntity<>(userService.getDiary(userID, diaryID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{userID}/diary/{diaryID}/entry/all-entries")
    public ResponseEntity<?> getAllEntries(@PathVariable String userID, @PathVariable String diaryID) {
        try {
            return new ResponseEntity<>(userService.getAllEntries(userID, diaryID), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{userID}/diary/{diaryID}/entry/{entryID}")
    public ResponseEntity<?> getEntry(@PathVariable String userID, @PathVariable String diaryID, @PathVariable String entryID) {
        try {
            return new ResponseEntity<>(userService.getEntry(userID, diaryID, entryID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}

