package com.example.heroku.encoder;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/encoder")
public class EncoderController {

    private final Logger logger = LoggerFactory.getLogger(EncoderController.class);

    private final EncoderService service;

    public EncoderController(EncoderService service) {
        this.service = service;
    }

    @GetMapping("/test")
    String test() {
        return "Special for Ayuaniche";
    }

    @GetMapping("/encode")
    ResponseEntity<String> encode(@RequestParam String iin) {
        logger.info("encoding " + iin);
        return ResponseEntity.ok(service.encode(iin));
    }

    @PostMapping("/decode")
    ResponseEntity<String> decode(@RequestBody EncoderDTO encoderDTO) {
        logger.info("decoding " + encoderDTO.getIin());
        return ResponseEntity.ok(service.decode(encoderDTO.getIin()));
    }


}
