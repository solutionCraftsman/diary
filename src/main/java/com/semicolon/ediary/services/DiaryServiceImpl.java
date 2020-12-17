package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.models.Diary;
import com.semicolon.ediary.models.Entry;
import com.semicolon.ediary.repositories.DiaryRepository;
import com.semicolon.ediary.repositories.EntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return createDiary(newDiary);
    }

    @Override
    public Diary addNewEntry(CreateEntryRequestModel createEntryRequestModel, String diaryId) throws Exception {
        Entry newEntry = new Entry();
        logger.warn(diaryId);
        Optional<Diary> entryDiary = diaryRepository.findDiaryById(diaryId);
        logger.warn(entryDiary.toString());
        if(entryDiary.isPresent()) {
            newEntry.setTitle(createEntryRequestModel.getTitle());
            newEntry.setBody(createEntryRequestModel.getBody());
            newEntry.setLocalDateTime(LocalDateTime.now());
            entryDiary.get().getEntries().add(entryService.saveEntryBeforeAddingToDiary(newEntry));
            return saveEntry(entryDiary.get());
        }
        else {
            throw new Exception("Diary Not Found");
        }
    }

    private Diary saveEntry(Diary entryDiary) {
        return diaryRepository.save(entryDiary);
    }

    private Diary createDiary(Diary diary) {
        return diaryRepository.save(diary);
    }
}
