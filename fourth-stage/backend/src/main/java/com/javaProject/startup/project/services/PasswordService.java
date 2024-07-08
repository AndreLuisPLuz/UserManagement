package com.javaProject.startup.project.services;

public interface PasswordService {
    String applyCryptography(String password);
    Boolean verifyCriptography(String password, String encryptedPassword);
    Boolean verifyRules(String password);
}
