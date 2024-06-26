package com.trevis.startup.example.dto.requests;

public record UserCreationDto(
    String username,
    Integer role,
    Long departmentId
) {}