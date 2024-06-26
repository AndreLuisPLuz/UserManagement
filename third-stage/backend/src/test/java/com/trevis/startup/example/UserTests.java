package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.entities.UserEntity;
import com.trevis.startup.example.enums.UserEntityRole;
import com.trevis.startup.example.interfaces.DepartmentEntityService;
import com.trevis.startup.example.interfaces.PasswordService;
import com.trevis.startup.example.interfaces.UserEntityService;

@SpringBootTest
public class UserTests {
    
    @Autowired
    UserEntityService userRepo;

    @Autowired
    DepartmentEntityService departRepo;

    @Autowired
    PasswordService passRepo;

    @Test
    void createUserTest() {
        var user = new UserEntity();
        user.setId(1l);
        user.setUsername("Silva");
        user.setRole(UserEntityRole.ADMINISTRATOR);
        user.setDepartment(departRepo.get().get(1));
        user.setPassword(passRepo.applyCryptography("SenhaTOP123"));
    
        // método alterado
        // userRepo.create(user);

        assertTrue(userRepo.get(user.getUsername()) instanceof UserEntity);
        assertEquals(userRepo.get(user.getUsername()).getUsername(), user.getUsername());
    }

    @Test
    void verifyCryptographyTest() {
        var user = new UserEntity();
        user.setId(1l);
        user.setUsername("Silva");
        user.setRole(UserEntityRole.ADMINISTRATOR);
        user.setDepartment(departRepo.get().get(1));
        user.setPassword(passRepo.applyCryptography("SenhaTOP123"));
        // método alterado
        // userRepo.create(user);

        // var findedUser = userRepo.get(user.getUsername());

        // assertTrue(passRepo.verifyCryptography(
        //     "SenhaTOP123", findedUser.getPassword()
        // ));
    }

    @Test
    void updatePasswordTest() {
        var user = new UserEntity();
        user.setId(1l);
        user.setUsername("Silva");
        user.setRole(UserEntityRole.ADMINISTRATOR);
        user.setDepartment(departRepo.get().get(1));
        user.setPassword(passRepo.applyCryptography("SenhaTOP123"));
        // método alterado
        // userRepo.create(user);

        // assertTrue(userRepo.update(user.getId(), "123Senhaaaa"));

        // var findedUser = userRepo.get(user.getUsername());
        // assertTrue(passRepo.verifyCryptography(
        //     "123Senhaaaa", findedUser.getPassword()
        // ));
    }
}
