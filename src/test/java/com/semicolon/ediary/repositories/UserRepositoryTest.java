package com.semicolon.ediary.repositories;

import com.semicolon.ediary.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class UserRepositoryTest {

    //given
    private User user = new User();;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        //..given
        user.setUsername("ayo");
        user.setPassword("testP@ssword");
        user.setEmail("test@email.com");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSave() {
        //when
        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals(user, savedUser);
    }
}