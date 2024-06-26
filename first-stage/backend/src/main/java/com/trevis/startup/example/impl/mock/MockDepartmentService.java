package com.trevis.startup.example.impl.mock;

import java.util.ArrayList;
import java.util.List;

import com.trevis.startup.example.model.Department;
import com.trevis.startup.example.services.DepartmentService;
import com.trevis.startup.example.structure.DataResult;

public class MockDepartmentService implements DepartmentService {
    private List<Department> departmentBase;

    public MockDepartmentService() {
        departmentBase = new ArrayList<>();

        var department1 = new Department();
        department1.setName("Engineering Technical School");
        department1.setAcronym("ETS");

        var department2 = new Department();
        department2.setName("Business Digital Organization");
        department2.setAcronym("BDO");

        var department3 = new Department();
        department3.setName("Information Coordination Organization");
        department3.setAcronym("ICO");

        departmentBase.add(department1);
        departmentBase.add(department2);
        departmentBase.add(department3);
    }

    public DataResult<Department> GetAll() {
        return new DataResult.Ok<>("Departments retrieved with success", departmentBase);
    }
}
