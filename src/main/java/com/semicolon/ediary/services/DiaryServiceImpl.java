package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.models.Diary;
import com.semicolon.ediary.models.Entry;
import com.semicolon.ediary.repositories.DiaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class DiaryServiceImpl implements DiaryService {
    Logger logger = LoggerFactory.getLogger(DiaryServiceImpl.class);

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private EntryService entryService;

    @Override
    public Diary createNewDiary(CreateDiaryRequestModel createDiaryRequestModel) {
        Diary newDiary = new Diary();
        newDiary.setNameOfOwner(createDiaryRequestModel.getNameOfOwner());
        newDiary.setLocalDateTime(LocalDateTime.now());
        return saveDiary(newDiary);
    }

    @Override
    public Entry createNewEntry(CreateEntryRequestModel createEntryRequestModel, String diaryID) throws Exception {
        logger.warn(diaryID);
        Optional<Diary> entryDiary = diaryRepository.findDiaryById(diaryID);
        logger.warn(entryDiary.toString());
        if (entryDiary.isPresent()) {
            Entry savedEntry = entryService.createNewEntry(createEntryRequestModel);
            entryDiary.get().getEntries().add(savedEntry);
            saveDiary(entryDiary.get());
            return savedEntry;
        } else {
            throw new Exception("Diary Not Found");
        }
    }

    @Override
    public Diary findDiaryById(String id) throws Exception {
        Optional<Diary> foundDiary = diaryRepository.findDiaryById(id);
        if(foundDiary.isPresent()) {
            return foundDiary.get();
        } else {
            throw new Exception("Diary not found");
        }
    }

    @Override
    public List<Entry> getAllEntries(String diaryID) throws Exception {
        Diary foundDiary = findDiaryById(diaryID);
        return foundDiary.getEntries();
    }

    public Entry getEntry(String diaryID, String entryID) throws Exception {
        Diary foundDiary = findDiaryById(diaryID);
        Optional<Entry> foundEntry = entryService.findEntryById(entryID);
        if(foundEntry.isPresent()) {
            if(foundDiary.getEntries().contains(foundEntry.get())) {
                return foundEntry.get();
            }
            else {
                throw new Exception("Entry does not belong to this diary");
            }
        }
        else {
            throw new Exception("Entry not found");
        }
    }

    /*private Diary saveEntry(Diary entryDiary) {
        return diaryRepository.save(entryDiary);
    }*/

    private Diary saveDiary(Diary diary) {
        return diaryRepository.save(diary);
    }
}
