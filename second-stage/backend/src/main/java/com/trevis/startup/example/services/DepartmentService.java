package com.trevis.startup.example.services;

import java.util.List;

import com.trevis.startup.example.exceptions.NoSuchEntityException;
import com.trevis.startup.example.model.Department;

public interface DepartmentService {
    Department getById(Long id) throws NoSuchEntityException;
    List<Department> getAll();
    public Department createDepartment(String name);
}
