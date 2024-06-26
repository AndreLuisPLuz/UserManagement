package com.trevis.startup.example.dto.requests;

public record UserUpdateDto(
    String password,
    Integer role,
    Long departmentId
) {}
