package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.services.AuthService;

@SpringBootTest
class AuthServiceTests {

    @Autowired
    AuthService authservice;

    @Test
    void authServiceTest(){
        assertNotEquals(authservice.login("Joao", "JoaoEmoo8"), null);
        assertEquals(authservice.login("Joao", "null"), null);
    }
    
}
