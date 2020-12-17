package com.semicolon.ediary.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class CreateDiaryRequestModel {
    private String nameOfOwner;
}
