package com.trevis.startup.example.services;

import com.trevis.startup.example.model.Department;
import com.trevis.startup.example.structure.DataResult;

public interface DepartmentService {
    DataResult<Department> GetAll();
}
