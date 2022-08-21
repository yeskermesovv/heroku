package com.example.heroku.encoder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/encoder")
public class Encoder {

    private final EncoderService service;

    public Encoder(EncoderService service) {
        this.service = service;
    }

    @GetMapping("/test")
    String test() {
        return "Special for Ayuaniche";
    }

    @GetMapping("/encode")
    ResponseEntity<String> encode(@RequestParam String iin) {
        return ResponseEntity.ok(service.encode(iin));
    }

    @GetMapping("/decode")
    ResponseEntity<String> decode(@RequestParam String iin) {
        return ResponseEntity.ok(service.decode(iin));
    }


}
