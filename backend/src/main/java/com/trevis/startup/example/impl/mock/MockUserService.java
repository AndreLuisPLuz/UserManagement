package com.trevis.startup.example.impl.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.startup.example.model.Department;
import com.trevis.startup.example.model.EmployeeType;
import com.trevis.startup.example.model.User;
import com.trevis.startup.example.services.PasswordService;
import com.trevis.startup.example.services.UserService;
import com.trevis.startup.example.structure.ArrayMessageResult;
import com.trevis.startup.example.structure.DataResult;

public class MockUserService implements UserService{
    List<User> userBase;
    List<EmployeeType> employeeTypesBase;

    @Autowired
    MockDepartmentService mockDepartmentService;

    @Autowired
    PasswordService passwordService;

    public MockUserService() throws Exception {
        userBase = new ArrayList<>();
        employeeTypesBase = new ArrayList<>();

        var administrador = new EmployeeType();
        administrador.setId(1l);
        administrador.setName("Administrador");

        var gerente = new EmployeeType();
        gerente.setId(2l);
        gerente.setName("Gerente");

        var colaborador = new EmployeeType();
        colaborador.setId(3l);
        colaborador.setName("Colaborador");

        employeeTypesBase.add(administrador);
        employeeTypesBase.add(gerente);
        employeeTypesBase.add(colaborador);

        DataResult<Department> getAllDepartmentsResult = mockDepartmentService.GetAll();
        List<Department> allDepartments;

        switch (getAllDepartmentsResult) {
            case DataResult.Ok<Department> r -> { allDepartments = r.data(); }
            case DataResult.Error<Department> error -> { throw new Exception("Failed to fetch departments."); }
        };

        Department bdo = allDepartments.get(1);

        var user1 = new User();
        user1.setId(1l);
        user1.setName("Yasmin Trembulack");
        user1.setEmail("yasmin@gmail.com");
        String hashedPassword ="senha";

        // // -------- Tirar o comentario quando o PasswordService estiver implementado --------

        // hashedPassword = passwordService.applyCryptography(hashedPassword);

        user1.setHashedPassword(hashedPassword);
        user1.setEmployeeType(employeeTypesBase.get(0));
        user1.setDepartment(bdo);

        userBase.add(user1);
        
    }

    @Override
    public ArrayMessageResult Create(User user) {
        List<String> messages = new ArrayList<>();

        // // -------- Tirar o comentario quando o PasswordService estiver implementado --------

        // if (passwordService.verifyRules(user.getHashedPassword()) != 5){
        //     messages.add("Weak password.");
        //     messages.add(String.format("%d", passwordService.verifyRules(user.getHashedPassword())));
        //     return new ArrayMessageResult.Error(400, messages);
        // }

        userBase.add(user);
        messages.add("User created with sucess.");
        return new ArrayMessageResult.Ok(messages);
    }

    @Override
    public ArrayMessageResult UpdatePassword(User user, String newPassword) {
        User correctUser = null;
        List<String> messages = new ArrayList<>();

        for (User u : userBase) {
            if (u.getName().equals(user.getName()) && u.getHashedPassword().equals(user.getHashedPassword())) {
                correctUser = u;
                break;
            }
        }

        if (correctUser == null) {
            messages.add("User not found.");
            return new ArrayMessageResult.Error(404, messages);
        }


        // // -------- Tirar o comentario quando o PasswordService estiver implementado --------

        // if (!passwordService.verifyCryptography(newPassword, user.getHashedPassword())){
        //     messages.add("Incorrect password.");
        //     return new ArrayMessageResult.Error(400, messages);
        // }
    
        // if (passwordService.verifyRules(newPassword) != 5){
        //     messages.add("Weak password.");
        //     messages.add(String.format("%d", passwordService.verifyRules(newPassword)));
        //     return new ArrayMessageResult.Error(400, messages);
        // }

        // newPassword = passwordService.applyCryptography(newPassword);

        user.setHashedPassword(newPassword);

        messages.add("Password successfully changed.");
        return new ArrayMessageResult.Ok(messages);
    }

    @Override
    public DataResult<User> Get(String username) {
        List<User> matchingUsers = new ArrayList<>();

        for (User u : userBase) {
            if (u.getName().contains(username) ) {
                matchingUsers.add(u);
            }
        }

        if (matchingUsers.size() == 0)
            return new DataResult.Error<>(404, "No matching users found.");

        return new DataResult.Ok<>("Matching users found.", matchingUsers);
    }
}
