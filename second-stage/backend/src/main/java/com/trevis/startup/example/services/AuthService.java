package com.trevis.startup.example.services;

import com.trevis.startup.example.dto.AuthToken;

public interface AuthService {
    public AuthToken login(String username, String password);
}
