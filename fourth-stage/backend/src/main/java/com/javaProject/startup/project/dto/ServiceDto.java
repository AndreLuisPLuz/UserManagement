package com.javaProject.startup.project.dto;

import com.javaProject.startup.project.model.Service;

public record ServiceDto(
    Long id,
    String name,
    String description,
    Boolean internal,
    UserDto manager
) {
    public static ServiceDto buildFromEntity(Service service) {
        return new ServiceDto(
            service.getId(),
            service.getName(),
            service.getDescription(),
            service.getIntern(),
            UserDto.buildFromEntity(service.getManager())
        );
    }
}
