package com.semicolon.ediary.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    //@GetMapping("/")
    @RequestMapping("/")
    public String index() {
        return "<h1>Hallo! Greetings from Spring Boot!</h1>";
    }
}
