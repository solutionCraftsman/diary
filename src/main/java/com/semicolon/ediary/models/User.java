package com.semicolon.ediary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    private String id;

    private String username;
    private String email;
    private String password;

    @DBRef
    private List<Diary> diaries = new ArrayList<>();
}
