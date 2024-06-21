package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.services.PasswordService;

@SpringBootTest
class PasswordServiceTest {

    @Autowired
    PasswordService passService;

    @Test
    void applyCryptographyTest(){
        String cryptografy = (passService.applyCryptography("123"));
        assertTrue(passService.verifyCryptography("123", cryptografy));
    }

    @Test
    void verifyRulesTest(){
        assertEquals(passService.verifyRules("@"), "1");
        assertEquals(passService.verifyRules("joao"), "2");
        assertEquals(passService.verifyRules("joaoemoo"), "3");
        assertEquals(passService.verifyRules("Joaoemoo"), "4");
        assertEquals(passService.verifyRules("JoaoEmoo8"), "5");
    }

    
}
