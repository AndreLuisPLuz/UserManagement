package com.trevis.startup.example.exceptions;

public class UnauthorizedException extends ResponseException {
    public UnauthorizedException() {
        super("Requested service was not authorized due to authentication issues", 401);
    }
}
