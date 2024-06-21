package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.services.AuthService;
import com.trevis.startup.example.structure.SimpleMessageResult;

@SpringBootTest
public class AuthServiceTests {

    @Autowired
    AuthService service;

    @Test
    private void authServiceLoginTest(){

        // correct username and password
        var result = service.login("Yasmin Trembulack", "senha@123");

        Boolean wasLoginSuccessfully = switch (result) {
            case SimpleMessageResult.Ok r -> true;
            case SimpleMessageResult.Error error -> false;
        };
        assertTrue(wasLoginSuccessfully);

        // wrong username and password
        result = service.login("errado", "Errado@123");

        wasLoginSuccessfully = switch (result) {
            case SimpleMessageResult.Ok r -> true;
            case SimpleMessageResult.Error error -> false;
        };
        assertFalse(wasLoginSuccessfully);

        // wrong username and correct password
        result = service.login("errado", "senha@123");

        wasLoginSuccessfully = switch (result) {
            case SimpleMessageResult.Ok r -> true;
            case SimpleMessageResult.Error error -> false;
        };
        assertFalse(wasLoginSuccessfully);

        // correct username and wrong password
        result = service.login("Yasmin Trembulack", "Errado@123");

        wasLoginSuccessfully = switch (result) {
            case SimpleMessageResult.Ok r -> true;
            case SimpleMessageResult.Error error -> false;
        };
        assertFalse(wasLoginSuccessfully);
    }
}
