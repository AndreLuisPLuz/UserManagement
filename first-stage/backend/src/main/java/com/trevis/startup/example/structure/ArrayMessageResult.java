package com.trevis.startup.example.structure;

import java.util.List;

public sealed interface ArrayMessageResult {
    public record Ok(List<String> messages) implements ArrayMessageResult { };
    public record Error(Integer errorCode, List<String> messages) implements ArrayMessageResult { };
}
