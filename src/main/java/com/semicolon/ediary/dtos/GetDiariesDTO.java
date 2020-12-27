package com.semicolon.ediary.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class GetDiariesDTO {
    private String ownerName;
}
