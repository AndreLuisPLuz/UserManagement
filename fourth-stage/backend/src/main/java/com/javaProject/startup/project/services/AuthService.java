package com.javaProject.startup.project.services;

import java.security.NoSuchAlgorithmException;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.javaProject.startup.project.dto.AuthToken;
import com.javaProject.startup.project.exceptions.BadHashConfigurationException;
import com.javaProject.startup.project.exceptions.ClaimsConversionException;
import com.javaProject.startup.project.exceptions.InvalidAuthAttempt;
import com.javaProject.startup.project.exceptions.NoSuchEntityException;

public interface AuthService {
    AuthToken login(String username, String password)
        throws
                NoSuchAlgorithmException,
                ClaimsConversionException,
                NoSuchEntityException,
                BadHashConfigurationException;

    DecodedJWT decode(String token)
        throws
            NoSuchAlgorithmException,
            InvalidAuthAttempt;
}
