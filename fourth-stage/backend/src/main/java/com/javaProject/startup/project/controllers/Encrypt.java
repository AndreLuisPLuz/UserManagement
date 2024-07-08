package com.javaProject.startup.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javaProject.startup.project.services.AuthService;

// @ControllerAdvice
@RestController
public class Encrypt {

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/encrypt/{token}")
    public String tryLogin(@PathVariable String token) {
        return encoder.encode(token);
    }
}
