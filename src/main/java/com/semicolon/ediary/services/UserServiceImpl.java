package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.dtos.CreateUserRequestModel;
import com.semicolon.ediary.models.Diary;
import com.semicolon.ediary.models.Entry;
import com.semicolon.ediary.models.User;
import com.semicolon.ediary.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    DiaryService diaryService;

    @Autowired
    UserService userService;

    @Override
    public User createNewUser(CreateUserRequestModel createUserRequestModel) throws Exception {
        if (usernameExists(createUserRequestModel.getUsername()) ||
                emailExists(createUserRequestModel.getEmail())) {
            throw new Exception("username or email already exists");
        }

        User newUser = new User();
        newUser.setUsername(createUserRequestModel.getUsername());
        newUser.setEmail(createUserRequestModel.getEmail());
        newUser.setPassword(createUserRequestModel.getPassword());

        return saveUser(newUser);
    }

    @Override
    public Diary createNewDiary(CreateDiaryRequestModel createDiaryRequestModel, String userID) throws Exception {
        User foundUser = findUserById(userID);

        Diary savedDiary = diaryService.createNewDiary(createDiaryRequestModel);
        foundUser.getDiaries().add(savedDiary);
        saveUser(foundUser);
        return savedDiary;
    }

    private User findUserById(String id) throws Exception {
        Optional<User> foundUser = userRepository.findUserById(id);
        if (foundUser.isPresent()) {
            return foundUser.get();
        } else {
            throw new Exception("User not found");
        }
    }

    @Override
    public Entry createNewDiaryEntry(CreateEntryRequestModel createEntryRequestModel, String userID, String diaryID) throws Exception {
        //find user
        User foundUser = findUserById(userID);
        //confirm diary belongs to user
        Optional<Diary> foundDiary = diaryService.findDiaryById(diaryID);
        if (foundDiary.isPresent()) {
            if (foundUser.getDiaries().contains(foundDiary.get())) {
                return diaryService.createNewEntry(createEntryRequestModel, diaryID);
            } else {
                throw new Exception("Diary not found to belong to this user");
            }
        } else {
            throw new Exception("Diary not found");
        }
    }

    private User saveUser(User user) {
        return userRepository.save(user);
    }

    private boolean usernameExists(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }

    private boolean emailExists(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    @Override
    public List<Diary> getAllDiaries(String userID) throws Exception {
        User foundUser = findUserById(userID);
        logger.warn(foundUser.toString());
        return foundUser.getDiaries();
    }

    @Override
    public Diary getDiary(String userID, String diaryID) throws Exception {
        User foundUser = findUserById(userID);
        Optional<Diary> foundDiary = diaryService.findDiaryById(diaryID);
        if (foundDiary.isPresent()) {
            if (foundUser.getDiaries().contains(foundDiary.get())) {
                return foundDiary.get();
            } else {
                throw new Exception("Diary does not belong to this user");
            }
        } else {
            throw new Exception("Diary not found");
        }
    }

    @Override
    public List<Entry> getAllEntries(String userID, String diaryID) throws Exception {
        getDiary(userID, diaryID);
        return diaryService.getAllEntries(diaryID);
    }
}







