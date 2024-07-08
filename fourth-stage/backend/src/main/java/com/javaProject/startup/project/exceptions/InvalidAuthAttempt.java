package com.javaProject.startup.project.exceptions;

public class InvalidAuthAttempt extends Exception {
    public InvalidAuthAttempt(String explanation) {
        super(explanation);
    }
}