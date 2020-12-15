package com.semicolon.ediary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document //Create a table that will take this record
@Data
@AllArgsConstructor
@RequiredArgsConstructor //Also creates a no-args constructor!

public class Entry {
    @Id
    private String id;

    //@UniqueElements
    private String title;
    private String body;
    private LocalDateTime localDateTime;
}
