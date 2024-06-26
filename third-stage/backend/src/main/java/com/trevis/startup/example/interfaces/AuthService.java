package com.trevis.startup.example.interfaces;

import io.jsonwebtoken.Claims;

public interface AuthService {
    String login(String username, String password);
    Claims extractAllClaims(String token);
    Long extractUserId(String token);
}
