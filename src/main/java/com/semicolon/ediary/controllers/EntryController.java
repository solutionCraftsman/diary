package com.semicolon.ediary.controllers;

import com.semicolon.ediary.dtos.ApiResponse;
import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entry/")
public class EntryController {
    @Autowired
    //@Qualifier("defaultEntryServiceToBeUsed")
    private EntryService entryService;
    //OR
    //EntryService entryService = new EntryServiceImpl();

    @PostMapping("create")
    //public String saveNewEntry(@RequestBody Entry newEntry) {
    //public ResponseEntity<?> saveNewEntry(@RequestBody @Valid (then e.g. @Email) Entry newEntry) {
    //public ResponseEntity<?> saveNewEntry(@RequestBody Entry newEntry) {
    public ResponseEntity<?> saveNewEntry(@RequestBody CreateEntryRequestModel createEntryRequestModel) {
        //entryService.saveNewEntry(newEntry);
        //return "successful";
        //return new ResponseEntity<>(entryService.saveNewEntry(newEntry), HttpStatus.CREATED);
        //entryService.saveNewEntry(newEntry);
        /*if (*/ entryService.createNewEntry(createEntryRequestModel); /* != null) {*/
            return new ResponseEntity<>(new ApiResponse(true, "New Entry Created"), HttpStatus.CREATED);
        /*} else {
            return new ResponseEntity<>(new ApiResponse(false, "Entry Creation Failed"), HttpStatus.NOT_IMPLEMENTED);
        }*/
    }
}
