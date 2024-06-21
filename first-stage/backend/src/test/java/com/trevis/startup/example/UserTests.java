package com.trevis.startup.example;

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
import com.trevis.startup.example.services.DepartmentService;
// import com.trevis.startup.example.services.PasswordService;
import com.trevis.startup.example.services.UserService;
import com.trevis.startup.example.structure.ArrayMessageResult;
import com.trevis.startup.example.structure.DataResult;

@SpringBootTest(classes = {com.trevis.startup.example.Application.class})
public class UserTests {
    @Autowired
    UserService service;

    @Autowired
    DepartmentService departmentService;

    // @Autowired
    // PasswordService passwordService;

    List<EmployeeType> employeeTypesBase;

    // A razão pela qual a base de tipos de funcionários é simulada aqui é,
    // claro, que ainda não temos um JPARepository para ela, e o serviço
    // para ela não existe como requisito no trabalho. O ideal é substituí-la
    // quando o JPARepository for incluído.
    public UserTests() {
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
    public void userServiceCreateTest() {
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

        var result = service.Create(user);

        Boolean wasCreatedSuccessfully = switch (result) {
            case ArrayMessageResult.Ok r -> true;
            case ArrayMessageResult.Error error -> false;
        };

        assertTrue(wasCreatedSuccessfully);
    }

    @Test
    public void userPasswordChangeTest() {
        var userSearch = service.Get("Yasmin");
        List<User> matchingUsers = switch(userSearch) {
            case DataResult.Ok<User> r -> r.data();
            case DataResult.Error<User> error -> null;
        };

        assertNotNull(matchingUsers);

        var yasmin = matchingUsers.get(0);
        assertNotNull(yasmin);

        service.UpdatePassword(yasmin, "newpassword");

        // assertTrue(
        //     passwordService.verifyCryptography(
        //         "newpassword",
        //         yasmin.getHashedPassword()
        //         )
        // );
    }
}
