package com.trevis.startup.example.structure;

public sealed interface SimpleMessageResult {
    public record Ok(String message) implements SimpleMessageResult { };
    public record Error(Integer errorCode, String message) implements SimpleMessageResult { };
}
