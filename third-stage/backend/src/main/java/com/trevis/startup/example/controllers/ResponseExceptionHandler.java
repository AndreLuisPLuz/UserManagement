package com.trevis.startup.example.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.trevis.startup.example.exceptions.ResponseException;

@ControllerAdvice
public class ResponseExceptionHandler {
    
    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ResponseMessage> handleResponseException(ResponseException ex, WebRequest wr) {

        return ResponseEntity
            .status(ex.getStatusCode())
            .body(new ResponseMessage(ex.getMessage()));
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ResponseMessage> handleUnsupportedOperationException(ResponseException ex, WebRequest wr) {

        return ResponseEntity
            .status(501)
            .body(new ResponseMessage("Not implemented"));
    }

    public record ResponseMessage(String message) {}
}
