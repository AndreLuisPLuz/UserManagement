package com.trevis.startup.example.services;

import com.trevis.startup.example.structure.SimpleMessageResult;

public interface AuthService {
    SimpleMessageResult login(String username, String password);
    //retona o token
}
