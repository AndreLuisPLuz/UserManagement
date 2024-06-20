package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.dto.DepartmentDTO;
import com.trevis.startup.example.model.Department;
import com.trevis.startup.example.model.User;
import com.trevis.startup.example.services.DepartmentService;
import com.trevis.startup.example.services.UserService;
import com.trevis.startup.example.structure.DataResult;

@SpringBootTest
public class UserTests {
    @Autowired
    UserService service;

    List<Department> departmentsBase;

    public UserTests() {
        
    }

    @Test
    private void userServiceCreateTest() {
        var user = new User();
        user.setId(1l);
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");
        user.setHashedPassword("johndoe123");
        
        var departmentsFetch = departmentService.GetAll();
        List<DepartmentDTO> departmentDTOs = switch(departmentsFetch) {
            case DataResult.Ok<DepartmentDTO> r -> r.data();
            case DataResult.Error<DepartmentDTO> error -> null;
        };

        assertNotNull(departmentDTOs);



        user.setDepartment(departments.get(0));
    }
}
