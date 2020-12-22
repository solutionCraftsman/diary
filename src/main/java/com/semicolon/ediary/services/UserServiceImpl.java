package com.semicolon.ediary.services;

import com.semicolon.ediary.dtos.CreateDiaryRequestModel;
import com.semicolon.ediary.dtos.CreateEntryRequestModel;
import com.semicolon.ediary.dtos.CreateUserRequestModel;
import com.semicolon.ediary.models.Diary;
import com.semicolon.ediary.models.Entry;
import com.semicolon.ediary.models.User;
import com.semicolon.ediary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DiaryService diaryService;

    @Autowired
    UserService userService;

    @Override
    public User createNewUser(CreateUserRequestModel createUserRequestModel) throws Exception {
        if(usernameExists(createUserRequestModel.getUsername()) ||
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
    public Diary addNewDiary(CreateDiaryRequestModel createDiaryRequestModel, String userID) throws Exception {
        Optional<User> foundUser = findUserById(userID);
        if(foundUser.isPresent()) {
            Diary savedDiary = diaryService.createNewDiary(createDiaryRequestModel);
            foundUser.get().getDiaries().add(savedDiary);
            saveUser(foundUser.get());
            return savedDiary;
        } else {
            throw new Exception("User not found");
        }
    }

    private Optional<User> findUserById(String id) {
        return userRepository.findUserById(id);
    }

    @Override
    public Entry addNewDiaryEntry(CreateEntryRequestModel createEntryRequestModel, String userID, String diaryID) throws Exception {
        //find user
        Optional<User> foundUser = findUserById(userID);
        //confirm diary belongs to user
        if(foundUser.isPresent()) {
            Optional<Diary> foundDiary = diaryService.findDiaryById(diaryID);
            if(foundDiary.isPresent()) {
                if(foundUser.get().getDiaries().contains(foundDiary.get())) {
                    return diaryService.addNewEntry(createEntryRequestModel, diaryID);
                } else {
                    throw new Exception("Diary not found to belong to this user");
                }
            } else {
                throw new Exception("Diary not found");
            }
        } else {
            throw new Exception("User not found");
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
}
