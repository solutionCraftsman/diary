package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.models.Entry;
import org.springframework.stereotype.Service;


public interface EntryService {
    //Entry saveNewEntry(Entry entry);
    Entry saveNewEntry(CreateEntryRequestModel createEntryRequestModel);
    Entry saveEntryBeforeAddingToDiary(Entry entry);
}
