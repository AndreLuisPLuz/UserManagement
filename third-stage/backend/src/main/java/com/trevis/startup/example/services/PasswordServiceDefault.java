package com.trevis.startup.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.trevis.startup.example.exceptions.UnauthorizedException;
import com.trevis.startup.example.interfaces.PasswordService;

public class PasswordServiceDefault implements PasswordService {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);

    @Override
    public String applyCryptography(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public void verifyCryptography(String password, String encryptedPassword) {
        if(!passwordEncoder.matches(password, encryptedPassword)) {
            throw new UnauthorizedException();
        }
    }

    @Override
    public List<String> verifyRules(String password) {
        List<String> errors = new ArrayList<String>();
        boolean hasLetters = false;
        boolean hasNumbers = false;

        if (password.length() >= 8) {
            errors.add("Password must contain at least 8 characters");
        }

        if (password.toLowerCase().equals(password)) {
            errors.add("Password must contain lower case letters");
            hasLetters = true;
        }

        if (password.toUpperCase().equals(password)) {
            errors.add("Password must contain upper case letters");
            hasLetters = true;
        }

        if (!hasLetters) {
            errors.add("Password must contain letters");
        }

        for(int i = 0; i < password.length(); i++) {
            int current = password.charAt(i) - '0'; 
            if(current >= 0 || current <= 9) {
                hasNumbers = true;
                break;
            }
        }

        if (!hasNumbers) {
            errors.add("Password must contain numbers");
        }

        return errors;
    }
}
