package com.trevis.startup.example.services.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.startup.example.dto.requests.UserCreationDto;
import com.trevis.startup.example.dto.requests.UserUpdateDto;
import com.trevis.startup.example.entities.UserEntity;
import com.trevis.startup.example.enums.UserEntityRole;
import com.trevis.startup.example.interfaces.DepartmentEntityService;
import com.trevis.startup.example.interfaces.PasswordService;
import com.trevis.startup.example.interfaces.UserEntityService;

import jakarta.annotation.PostConstruct;

public class UserServiceMock implements UserEntityService {

    @Autowired
    PasswordService passRepo;

    @Autowired
    DepartmentEntityService departRepo;
    
    private final List<UserEntity> users = new ArrayList<>();

    public UserServiceMock(){}

    @PostConstruct
    public void init()
    {
        var user1 = new UserEntity();
        user1.setId(1l);
        user1.setUsername("Silva");
        user1.setRole(UserEntityRole.ADMINISTRATOR);
        user1.setDepartment(departRepo.get().get(1));
        user1.setPassword(passRepo.applyCryptography("SenhaTOP123"));
        users.add(user1);

        var user2 = new UserEntity();
        user2.setId(2l);
        user2.setUsername("Duda");
        user2.setRole(UserEntityRole.COLLABORATOR);
        user2.setDepartment(departRepo.get().get(2));
        user2.setPassword(passRepo.applyCryptography("SenhaTOP123"));
        users.add(user2);

        var user3 = new UserEntity();
        user3.setId(3l);
        user3.setUsername("Zago");
        user3.setRole(UserEntityRole.MANAGER);
        user3.setDepartment(departRepo.get().get(0));
        user3.setPassword(passRepo.applyCryptography("SenhaTOP123"));
        users.add(user3);
    }

    @Override
    public UserEntity get(String username) {
        return users.stream()
            .filter(u -> u.getUsername().contains(username))
            .toList().get(0);
    }

    @Override
    public UserEntity create(UserCreationDto payload, Long userId) {
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public UserEntity get(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public UserEntity update(Long id, UserUpdateDto payload, Long userId) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}