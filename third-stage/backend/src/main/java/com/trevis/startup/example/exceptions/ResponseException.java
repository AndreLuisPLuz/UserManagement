package com.trevis.startup.example.exceptions;

public class ResponseException extends RuntimeException {
    
    private final String message;
    private final Integer statusCode;

    public ResponseException(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
