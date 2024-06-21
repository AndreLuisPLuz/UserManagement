package com.trevis.startup.example.dto;

import com.trevis.startup.example.model.Department;

public record DepartmentDTO(Long id, String name, String acronym) {
    public static DepartmentDTO buildFromModel(Department department) {
        return new DepartmentDTO(
            department.getId(),
            department.getName(),
            department.getAcronym()
        );
    }
}
