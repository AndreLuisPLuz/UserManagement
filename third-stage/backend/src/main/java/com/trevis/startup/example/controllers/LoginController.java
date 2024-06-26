package com.trevis.startup.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.example.dto.requests.LoginDto;
import com.trevis.startup.example.interfaces.AuthService;

@RestController
@RequestMapping("/auth")
public class LoginController {
    
    @Autowired
    AuthService authService;

    @GetMapping("")
    public ResponseEntity<String> login(@RequestBody LoginDto body) {

        String token = authService.login(body.username(), body.password());
    
        return ResponseEntity.ok(token);
    }
}
