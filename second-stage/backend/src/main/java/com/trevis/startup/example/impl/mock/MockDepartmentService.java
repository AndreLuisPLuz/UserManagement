package com.trevis.startup.example.impl.mock;

import java.util.ArrayList;
import java.util.List;

import com.trevis.startup.example.exceptions.NoSuchEntityException;
import com.trevis.startup.example.model.Department;
import com.trevis.startup.example.services.DepartmentService;

public class MockDepartmentService implements DepartmentService {
    
    private static List<Department> departments = new ArrayList<>();

    public MockDepartmentService() {
        var newDepartment = new Department();
        newDepartment.setId(1l);
        newDepartment.setName("IT Department");

        departments.add(newDepartment);

        newDepartment = new Department();
        newDepartment.setId(2l);
        newDepartment.setName("Finances Department");

        departments.add(newDepartment);

        newDepartment = new Department();
        newDepartment.setId(3l);
        newDepartment.setName("HR Department");

        departments.add(newDepartment);

    }
    
    @Override
    public List<Department> getAll(){
        return departments;
    }

    @Override
    public Department getById(Long id) throws NoSuchEntityException {
        for (Department department : departments) {
            if (id == department.getId()) 
                return department;
        }
        return null;
    }
}
