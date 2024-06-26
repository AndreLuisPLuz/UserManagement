package com.trevis.startup.example.interfaces;

import java.util.List;

public interface PasswordService {
    String applyCryptography(String password);
    void verifyCryptography(String password, String encryptedPassword);
    List<String> verifyRules(String password);
}