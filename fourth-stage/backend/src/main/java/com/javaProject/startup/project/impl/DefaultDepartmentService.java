package com.javaProject.startup.project.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.javaProject.startup.project.model.Department;
import com.javaProject.startup.project.repositories.DepartmentRepository;
import com.javaProject.startup.project.services.DepartmentService;

public class DefaultDepartmentService implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepo;

    @Override
    public List<Department> getAll() {
        return departmentRepo.findAll();
    }
    
}
