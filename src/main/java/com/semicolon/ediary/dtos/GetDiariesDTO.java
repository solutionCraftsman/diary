package com.semicolon.ediary.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class GetDiariesDTO {
    private String ownerName;
}
