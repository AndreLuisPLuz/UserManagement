package com.trevis.startup.example.exceptions;

public class NotFoundException extends ResponseException {
    public NotFoundException() {
        super("Requested entity not found", 404);
    }
}
