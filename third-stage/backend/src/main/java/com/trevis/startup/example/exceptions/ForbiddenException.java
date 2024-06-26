package com.trevis.startup.example.exceptions;

public class ForbiddenException extends ResponseException {
    public ForbiddenException() {
        super("Your user does not own enough authorization to request that", 403);
    }
}
