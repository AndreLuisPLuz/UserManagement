package com.javaProject.startup.project.services;

import com.javaProject.startup.project.exceptions.BadHashConfigurationException;

public interface PasswordService {
    String applyCryptography(String password)
        throws
            BadHashConfigurationException;

    Boolean verifyCriptography(String password, String encryptedPassword)
        throws
            BadHashConfigurationException;

    Boolean verifyRules(String password);
}
