package com.trevis.startup.example.structure;

public sealed interface HttpResult<T> {
    public record Ok<T>(T data) implements HttpResult<T> { };
    public record Error<T>(Integer errorCode, String message) implements HttpResult<T> { };
}
