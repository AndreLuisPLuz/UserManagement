package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.services.PasswordService;

@SpringBootTest
public class PasswordTests {
    @Autowired
    PasswordService service;

    @Test
    public void passwordCryptographyTest() {
        String plainPassword = "Ivzc@ghj27&!!";
        String hashedPassword = service.applyCryptography(plainPassword);

        assertTrue(service.verifyCryptography(plainPassword, hashedPassword));
    }

    @Test
    public void passwordWeakVerificationTest() {
        String plainPassword = "password";
        Integer passwordStrength = service.verifyRules(plainPassword);

        assertNotEquals(passwordStrength, 5);
    }

    @Test
    public void passwordStrongVerificationTest() {
        String plainPassword = "Ivzc@ghj27&!!";
        Integer passwordStrength = service.verifyRules(plainPassword);

        assertEquals(passwordStrength, 5);
    }
}
