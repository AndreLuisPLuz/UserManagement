package com.javaProject.startup.example;

import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.javaProject.startup.project.services.PasswordService;

@SpringBootTest
class PasswordTest {
    
	@Autowired 
	PasswordService pwd;	

    @Test
    public void applyCryptographyTest() {
       String passwordACT = "123pwdets";
       String encryptedPwd = pwd.applyCryptography(passwordACT);

       assertNotEquals(passwordACT, encryptedPwd);
    }	

    @Test
    public void verifyCriptographyTest() {
        String passwordVCT = "123pwdets";
        String encryptedPwd = pwd.applyCryptography(passwordVCT);

        Boolean isCorrect = pwd.verifyCriptography(passwordVCT, encryptedPwd);

        assertTrue(isCorrect);
    }	

    @Test
    public void verifyRulesTest() {
        String validPwd = "123pwdets";
        String invalidPwd = "123456javaehruim";

        Boolean isValidOne = pwd.verifyRules(validPwd);
        Boolean isValidTwo = pwd.verifyRules(invalidPwd);

        assertTrue(isValidOne);
        assertFalse(isValidTwo);
    }	
}

