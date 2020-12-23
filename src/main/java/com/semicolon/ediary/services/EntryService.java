package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.models.Entry;
import org.springframework.stereotype.Service;


public interface EntryService {
    //Entry createNewEntry(Entry entry);
    Entry createNewEntry(CreateEntryRequestModel createEntryRequestModel);
}
