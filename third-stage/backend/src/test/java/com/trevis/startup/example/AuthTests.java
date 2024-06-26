package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.entities.UserEntity;
import com.trevis.startup.example.interfaces.AuthService;
import com.trevis.startup.example.interfaces.UserEntityService;

@SpringBootTest
public class AuthTests {

    @Autowired
    AuthService authService;

    @Autowired
    UserEntityService userService;

    @Test
    void loginTest() {
        UserEntity user = new UserEntity();
        user.setUsername("Username");
        user.setPassword("Senha");
        // userService.create(user);

        String token = authService.login("Username", "Senha");

        assertNotNull(token);
    }
}
