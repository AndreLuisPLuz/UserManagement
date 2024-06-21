package com.trevis.startup.example.services;

public interface PasswordService {
    String applyCryptography(String password);
    Boolean verifyCryptography(String password, String encryptedPassword);
    Integer verifyRules(String password);
    // retorna integer posis precisa retornar a força da senha de 1 a 5. E ela só pode ser aceita se a força for = 5
}
