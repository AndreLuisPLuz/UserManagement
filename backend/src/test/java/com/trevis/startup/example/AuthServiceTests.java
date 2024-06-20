package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.model.Department;
import com.trevis.startup.example.model.EmployeeType;
import com.trevis.startup.example.model.User;
import com.trevis.startup.example.services.AuthService;
import com.trevis.startup.example.services.DepartmentService;
import com.trevis.startup.example.services.UserService;
import com.trevis.startup.example.structure.ArrayMessageResult;
import com.trevis.startup.example.structure.DataResult;
import com.trevis.startup.example.structure.SimpleMessageResult;

@SpringBootTest
public class AuthServiceTests {
    List<EmployeeType> employeeTypesBase;
    
    @Autowired
    DepartmentService departmentService;

    @Autowired
    AuthService service;

    @Autowired
    UserService userService;


    public AuthServiceTests(){
        employeeTypesBase = new ArrayList<>();

        var administrator = new EmployeeType();
        administrator.setName("Administrador");

        var manager = new EmployeeType();
        manager.setName("Gerente");

        var collaborator = new EmployeeType();
        collaborator.setName("Colaborador");

        employeeTypesBase.add(administrator);
        employeeTypesBase.add(manager);
        employeeTypesBase.add(collaborator);
    }

    @Test
    private void authServiceLoginTest(){
        var user = new User();
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");
        user.setHashedPassword("johndoe123");
        
        var departmentsFetch = departmentService.GetAll();
        List<Department> departments = switch(departmentsFetch) {
            case DataResult.Ok<Department> r -> r.data();
            case DataResult.Error<Department> error -> null;
        };

        assertNotNull(departments);

        user.setDepartment(departments.get(0));
        user.setEmployeeType(employeeTypesBase.get(0));

        var resultCreate = userService.Create(user);

        Boolean wasCreatedSuccessfully = switch (resultCreate) {
            case ArrayMessageResult.Ok r -> true;
            case ArrayMessageResult.Error error -> false;
        };
        assertTrue(wasCreatedSuccessfully);

        // correct username and password
        var result = service.login("John Doe", "johndoe123");

        Boolean wasLoginSuccessfully = switch (result) {
            case SimpleMessageResult.Ok r -> true;
            case SimpleMessageResult.Error error -> false;
        };
        assertTrue(wasLoginSuccessfully);

        // wrong username and password
        result = service.login("errado", "Errado@123");

        wasLoginSuccessfully = switch (result) {
            case SimpleMessageResult.Ok r -> true;
            case SimpleMessageResult.Error error -> false;
        };
        assertFalse(wasLoginSuccessfully);

        // wrong username and correct password
        result = service.login("errado", "johndoe123");

        wasLoginSuccessfully = switch (result) {
            case SimpleMessageResult.Ok r -> true;
            case SimpleMessageResult.Error error -> false;
        };
        assertFalse(wasLoginSuccessfully);

        // correct username and wrong password
        result = service.login("John Doe", "Errado@123");

        wasLoginSuccessfully = switch (result) {
            case SimpleMessageResult.Ok r -> true;
            case SimpleMessageResult.Error error -> false;
        };
        assertFalse(wasLoginSuccessfully);
    }
}
