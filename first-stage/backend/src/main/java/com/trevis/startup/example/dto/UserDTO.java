package com.trevis.startup.example.dto;

import com.trevis.startup.example.model.User;

public record UserDTO(Long id, String name, String employeeType, String departament) {
    public static UserDTO buildFromEntity(User user) {
        return new UserDTO(
            user.getId(),
            user.getName(),
            user.getEmployeeType().getName(),
            user.getDepartment().getName()
        );
    }
}
// Fazer a logica para retornar apenas o tipo e não o EmployeerType e o Department inteiro.
