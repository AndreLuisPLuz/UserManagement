package com.javaProject.startup.project.impl;

import com.javaProject.startup.project.services.HashService;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class SHA256HashService implements HashService {

    @Override
    public byte[] hash(String input) {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
            byte[] encodedhash = digest.digest(bytes);
            return encodedhash;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
