package com.semicolon.ediary.repositories;

import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.models.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiaryRepository extends MongoRepository<Diary, String> {
    Optional<Diary> findDiaryById(String id);
}
