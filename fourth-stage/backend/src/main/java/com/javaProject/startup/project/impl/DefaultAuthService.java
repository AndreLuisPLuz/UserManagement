package com.javaProject.startup.project.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.javaProject.startup.project.dto.AuthToken;
import com.javaProject.startup.project.dto.JWTUserData;
import com.javaProject.startup.project.exceptions.BadHashConfigurationException;
import com.javaProject.startup.project.model.UserData;
import com.javaProject.startup.project.repositories.UserRepository;
import com.javaProject.startup.project.services.AuthService;
import com.javaProject.startup.project.services.JWTService;
import com.javaProject.startup.project.services.PasswordService;

public class DefaultAuthService implements AuthService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordService passService;

    @Autowired
    JWTService<JWTUserData> jwtService;

    @Override
    public ResponseEntity<AuthToken> login(String username, String password) {
        UserData user = userRepo.findByUsername(username).get(0);

        if(user == null) {
            return new ResponseEntity<AuthToken>(
                new AuthToken(
                    "User not found :/",
                    null),
                HttpStatus.NOT_FOUND
            );
        }

        Boolean passwordMatches;
        try {
            passwordMatches = passService.verifyCriptography(password, user.getPassword());
        } catch(BadHashConfigurationException ex) {
            return ResponseEntity.badRequest().body(
                new AuthToken("Password didn't match.", null)
            );
        }
        
        if(!passwordMatches) {
            return new ResponseEntity<AuthToken>(
                new AuthToken(
                    "Incorrect password :/",
                    null),
                HttpStatus.BAD_REQUEST
            );
        }

        var token = jwtService.getToken(new JWTUserData(user.getId(), user.getRole()));
        
        return new ResponseEntity<AuthToken>(
            new AuthToken(
                "User logged successfully!",
                token),
            HttpStatus.OK
        );
    }
    
}
