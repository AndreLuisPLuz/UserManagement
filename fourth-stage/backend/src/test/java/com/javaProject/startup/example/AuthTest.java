package com.javaProject.startup.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaProject.startup.project.Application;
import com.javaProject.startup.project.model.UserData;
import com.javaProject.startup.project.services.AuthService;

@SpringBootTest(classes = Application.class)
class AuthTest {

    @Autowired
    private AuthService auth;

    @Test
    void loginTest() {
        String username = "user";
        String password = "password";

        // String user = auth.login(username, password);

        // assertNotNull(user, "User cannot be null after successful login.");
    }
}
