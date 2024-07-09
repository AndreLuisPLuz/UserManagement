package com.javaProject.startup.project.impl;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.javaProject.startup.project.dto.AuthToken;
import com.javaProject.startup.project.exceptions.BadHashConfigurationException;
import com.javaProject.startup.project.exceptions.ClaimsConversionException;
import com.javaProject.startup.project.exceptions.InvalidAuthAttempt;
import com.javaProject.startup.project.exceptions.NoSuchEntityException;
import com.javaProject.startup.project.model.UserData;
import com.javaProject.startup.project.repositories.UserRepository;
import com.javaProject.startup.project.services.AuthService;
import com.javaProject.startup.project.services.PasswordService;

public class Auth0JwtService implements AuthService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordService passwordService;

    static private KeyPair keyPair;

    static private void generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);

        keyPair = generator.generateKeyPair();
    }

    public AuthToken login(String username, String password)
            throws
                NoSuchAlgorithmException,
                ClaimsConversionException,
                NoSuchEntityException,
                BadHashConfigurationException {

        if (keyPair == null) {
            generateRSAKeyPair();
        }

        List<UserData> matchingUsers = userRepo.findByUsernameContaining(username);
        if (matchingUsers.size() == 0) {
            throw new NoSuchEntityException(
                "No user matches the given username."
            );
        }

        UserData user = matchingUsers.get(0);
        
        if (!passwordService.verifyCriptography(password, user.getPassword())) {
            return null;
        }

        var publicKey = (RSAPublicKey) keyPair.getPublic();
        var privateKey = (RSAPrivateKey) keyPair.getPrivate();

        String token;
        try {
            Algorithm algorithm = Algorithm.RSA512(publicKey, privateKey);
            token = JWT.create()
                .withIssuer("Andrezinho")
                .withClaim("userId", user.getId().toString())
                .withExpiresAt(Instant.now().plusSeconds(28800))
                .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new ClaimsConversionException(
                "Claims could not be converted."
            );
        }

        return new AuthToken("Login was successful", token);
    }

    public DecodedJWT decode(String token)
        throws
            NoSuchAlgorithmException,
            InvalidAuthAttempt {

        if (keyPair == null) {
            generateRSAKeyPair();
        }

        var publicKey = (RSAPublicKey) keyPair.getPublic();
        var privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Algorithm algorithm = Algorithm.RSA512(publicKey, privateKey);
        Verification verification = JWT.require(algorithm)
            .withIssuer("Andrezinho");

        try {
            JWTVerifier verifier = verification.build();
            return verifier.verify(token);
        } catch (JWTVerificationException ex) {
            throw new InvalidAuthAttempt("Invalid token");
        }
    }
}
