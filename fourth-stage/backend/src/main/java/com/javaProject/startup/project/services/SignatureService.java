package com.javaProject.startup.project.services;

public interface SignatureService {
    String sign(String message);
    boolean verify(String message, String signature);
}
