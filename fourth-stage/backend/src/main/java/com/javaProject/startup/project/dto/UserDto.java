package com.javaProject.startup.project.dto;

import com.javaProject.startup.project.model.UserData;

public record UserDto(
    Long id,
    String name,
    String department
) {
    public static UserDto buildFromEntity(UserData user) {
        return new UserDto(
            user.getId(),
            user.getUsername(),
            user.getDepartment().getName()
        );
    }
}
