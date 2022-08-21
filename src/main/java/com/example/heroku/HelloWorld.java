package com.example.heroku;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/encoder")
public class HelloWorld {

    @GetMapping("/test")
    String test() {
        return "Special for Ayuaniche";
    }

}
