package com.javaProject.startup.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaProject.startup.project.dto.AuthToken;
import com.javaProject.startup.project.dto.LoginData;
import com.javaProject.startup.project.services.AuthService;

@RestController
public class AuthController {
    
    @Autowired
    AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<AuthToken> tryLogin(@RequestBody LoginData obj) {
        AuthToken result;

        try {
            result = authService.login(obj.login(), obj.password());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(
                new AuthToken("Tentativa de login incorreta", null)
            );
        }

        if (result == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(result);
    }

    // ..nao esta implementado o retorno do erro ser no login/password..
}
