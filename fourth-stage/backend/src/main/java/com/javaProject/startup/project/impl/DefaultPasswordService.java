package com.javaProject.startup.project.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.javaProject.startup.project.services.PasswordService;


public class DefaultPasswordService implements PasswordService {

    @Autowired
    PasswordEncoder encoder;

    @Override
    public String applyCryptography(String password) {
        return encoder.encode(password);
    }

    @Override
    public Boolean verifyCriptography(String password, String encryptedPassword) {
        return encoder.matches(password, encryptedPassword) ? 
            true : 
            false;
    }

    @Override
    public Boolean verifyRules(String password) {
        if(!password.chars().anyMatch(Character::isDigit)) {
            return false;
        }
        
        if(!password.chars().anyMatch(Character::isUpperCase)) {
            return false;
        }

        if(!password.chars().anyMatch(Character::isLowerCase)) {
            return false;
        }

        if(password.length() < 8) {
            return false;
        }

        return true;
    }
    
}
