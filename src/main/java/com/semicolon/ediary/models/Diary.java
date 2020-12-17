package com.semicolon.ediary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Diary {
    @Id
    private String id;

    private String nameOfOwner;
    @DBRef
    private List<Entry> entries = new ArrayList<>();
    private LocalDateTime localDateTime;
}
