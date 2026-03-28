package org.example.spring_security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecuredController {
    @GetMapping("/health")
    public ResponseEntity<String> health(){
        return ResponseEntity.ok("Health check secured endpoint is working");
    }
}
