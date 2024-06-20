package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.model.Department;
import com.trevis.startup.example.services.DepartmentService;
import com.trevis.startup.example.structure.DataResult;

@SpringBootTest
public class DepartmentTests {
    @Autowired
    DepartmentService service;

    @Test
    private void getAllDepartmentsTest() {
        var result = service.GetAll();

        boolean wasFetchSuccessful;
        List<Department> fetchedDepartments;

        switch (result) {
            case DataResult.Ok<Department> r -> {
                wasFetchSuccessful = true;
                fetchedDepartments = r.data();
            }
            
            case DataResult.Error<Department> error -> {
                wasFetchSuccessful = false;
                fetchedDepartments = null;
            }
        };

        assertTrue(wasFetchSuccessful);
        assertNotNull(fetchedDepartments);

        int departmentsQty = fetchedDepartments.size();
        assertEquals(departmentsQty, 3);
    }
}
