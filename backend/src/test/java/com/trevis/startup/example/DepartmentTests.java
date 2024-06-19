package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.dto.DepartmentDTO;
import com.trevis.startup.example.services.DepartmentService;
import com.trevis.startup.example.structure.DataResult;

@SpringBootTest
public class DepartmentTests {
    @Autowired
    DepartmentService service;

    @Test
    void getAllDepartmentsTest() {
        var result = service.GetAll();

        boolean wasFetchSuccessful;
        List<DepartmentDTO> fetchedDepartments;

        switch (result) {
            case DataResult.Ok<DepartmentDTO> r -> {
                wasFetchSuccessful = true;
                fetchedDepartments = r.data();
            }
            
            case DataResult.Error<DepartmentDTO> error -> {
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
