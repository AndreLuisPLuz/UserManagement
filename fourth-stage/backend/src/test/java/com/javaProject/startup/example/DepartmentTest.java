package com.javaProject.startup.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaProject.startup.project.impl.mock.MockDepartmentService;
import com.javaProject.startup.project.model.Department;
import com.javaProject.startup.project.services.DepartmentService;
import java.util.*;

@SpringBootTest
class DepartmentTest {

    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        departmentService = new MockDepartmentService();
    }

    @Test
    public void getAllTest() {
        List<Department> departments = departmentService.getAll();

        assertEquals(4, departments.size()); 

        assertEquals("Controle de Qualidade", departments.get(0).getName());
        assertEquals("Engenharia", departments.get(1).getName());
        assertEquals("Logística", departments.get(2).getName());
        assertEquals("Recursos Humanos", departments.get(3).getName());
    }
}
