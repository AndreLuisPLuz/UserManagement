package com.trevis.startup.example.structure;

import java.util.List;

public sealed interface DataResult<T> {
    public record Ok<T>(String message, List<T> data) implements DataResult<T> { };
    public record Error<T>(Integer errorCode, String message) implements DataResult<T> { };
}
