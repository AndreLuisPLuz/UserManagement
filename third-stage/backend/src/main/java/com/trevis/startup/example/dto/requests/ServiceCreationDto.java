package com.trevis.startup.example.dto.requests;

public record ServiceCreationDto(
    String name,
    String description,
    boolean intern
) {}
