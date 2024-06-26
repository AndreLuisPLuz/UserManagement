package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.interfaces.PasswordService;

@SpringBootTest
public class PasswordTests {
    @Autowired
    PasswordService passRepo;

    @Test
    void applyCryptographyTest() {
        // String password = passRepo.applyCryptography("Senha123");
        
        // assertTrue(passRepo.verifyCryptography("Senha123", password));
        // assertFalse(passRepo.verifyCryptography("SenhaErrada", password));
    }

    @Test
    void verifyRulesTest() {
        assertEquals(4, passRepo.verifyRules("@#$").size());
        assertEquals(3, passRepo.verifyRules("Senhaaaa").size());
        assertEquals(2, passRepo.verifyRules("12345678").size());
        assertEquals(1, passRepo.verifyRules("senhasenha1").size());
        assertEquals(0, passRepo.verifyRules("Senha1234").size());
    }

}
