package com.metapod.http.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String home() {
        return "Hello, world!";
    }

    @GetMapping("/hi")
    public String hi() {
        return "hi, world!";
    }
}
