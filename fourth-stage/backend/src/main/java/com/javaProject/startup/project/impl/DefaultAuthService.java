package com.javaProject.startup.project.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.interfaces.DecodedJWT;
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
    public AuthToken login(String username, String password) {
        UserData user = userRepo.findByUsernameContaining(username).get(0);

        if(user == null) {
            return new AuthToken(
                "User not found :/",
                null
            );
        }

        Boolean passwordMatches;
        try {
            passwordMatches = passService.verifyCriptography(password, user.getPassword());
        } catch(BadHashConfigurationException ex) {
            return new AuthToken("Password didn't match.", null);
        }
        

        if(!passwordMatches) {
            new AuthToken(
        "Incorrect password :/",
                null
            );
        }

        var token = jwtService.getToken(new JWTUserData(user.getId(), user.getRole()));
        return new AuthToken(
                "User logged successfully!",
                token
            );
    }

    @Override
    public DecodedJWT decode(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'decode'");
    }
    
}
