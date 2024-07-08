package com.javaProject.startup.project.services;

import org.springframework.http.ResponseEntity;

import com.javaProject.startup.project.dto.AuthToken;
import com.javaProject.startup.project.model.UserData;

public interface AuthService {
    ResponseEntity<AuthToken> login(String username, String password);
}
