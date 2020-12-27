package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.models.Entry;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface EntryService {
    //Entry createNewEntry(Entry entry);
    Entry createNewEntry(CreateEntryRequestModel createEntryRequestModel);

    Optional<Entry> findEntryById(String id) throws Exception;

    //Entry getEntry(String diaryID, String entryID) throws Exception;
}
