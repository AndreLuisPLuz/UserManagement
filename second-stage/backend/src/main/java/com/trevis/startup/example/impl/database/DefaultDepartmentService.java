package com.trevis.startup.example.impl.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.startup.example.model.Department;
import com.trevis.startup.example.repositories.DepartmentJPARepository;
import com.trevis.startup.example.services.DepartmentService;

public class DefaultDepartmentService implements DepartmentService {

    @Autowired
    DepartmentJPARepository repo;

    @Override
    public List<Department> getAll() {
        return repo.findAll();
    }
}
