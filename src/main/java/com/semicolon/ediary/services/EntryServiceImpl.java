package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.models.Entry;
import com.semicolon.ediary.repositories.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
//@Service("defaultEntryServiceToUse")
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepository entryRepository;

    /*@Override
    public Entry saveNewEntry(Entry newEntry) {
        newEntry.setLocalDateTime(LocalDateTime.now());
        return saveEntry(newEntry);
    }*/

    @Override
    public Entry saveNewEntry(CreateEntryRequestModel createEntryRequestModel) {
        Entry newEntry = new Entry();
        newEntry.setTitle(createEntryRequestModel.getTitle());
        newEntry.setBody(createEntryRequestModel.getBody());
        return saveEntry(newEntry);
    }

    public Entry updateEntry(Entry updatedEntry) throws Exception {
        Optional<Entry> foundEntry = entryRepository.findById(updatedEntry.getId());
        if(foundEntry.isPresent()){
            foundEntry.get().setTitle(updatedEntry.getTitle());
            foundEntry.get().setBody(updatedEntry.getBody());
            return saveEntry(foundEntry.get());
        }
        else {
            throw new Exception("Not found");
        }
    }

    private Entry saveEntry(Entry entry) {
        return entryRepository.save(entry);
    }
}
