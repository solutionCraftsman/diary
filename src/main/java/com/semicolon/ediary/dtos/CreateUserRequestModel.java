package com.semicolon.ediary.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class CreateUserRequestModel {
    private String username;
    private String email;
    private String password;
}
