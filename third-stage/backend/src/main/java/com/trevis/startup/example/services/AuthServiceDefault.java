package com.trevis.startup.example.services;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.trevis.startup.example.entities.UserEntity;
import com.trevis.startup.example.exceptions.NotFoundException;
import com.trevis.startup.example.exceptions.UnauthorizedException;
import com.trevis.startup.example.interfaces.AuthService;
import com.trevis.startup.example.interfaces.PasswordService;
import com.trevis.startup.example.interfaces.UserEntityService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class AuthServiceDefault implements AuthService {

    @Autowired
    UserEntityService userService;
    @Autowired
    PasswordService passwordService;
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    
    @Override
    public String login(String username, String password) {
        
        UserEntity user = userService.get(username);

        try {
            passwordService.verifyCryptography(password, user.getPassword());
        } catch (NotFoundException ex) {
            throw new UnauthorizedException();
        }

        return Jwts
            .builder()
            .setId(user.getId().toString())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    @Override
    public Long extractUserId(String token) {
        token = token.replace("Bearer ", "").strip();
        Claims claims = extractAllClaims(token);
        return Long.parseLong(claims.getId());
    }

    @Override
    public Claims extractAllClaims(String token) {
        try {
            return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            throw new UnauthorizedException();
        }
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
