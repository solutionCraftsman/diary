package com.semicolon.ediary.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateEntryRequestModel {

    @Min(value = 3, message = "Title must be minimum of 3")
    @Max(value = 20, message = "How far na, you be Nebuchadnezzar")
    private String title;

    private String body;
}
