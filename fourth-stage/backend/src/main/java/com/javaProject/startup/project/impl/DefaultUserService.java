package com.javaProject.startup.project.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.javaProject.startup.project.exceptions.BadHashConfigurationException;
import com.javaProject.startup.project.model.Department;
import com.javaProject.startup.project.model.UserData;
import com.javaProject.startup.project.repositories.UserRepository;
import com.javaProject.startup.project.services.PasswordService;
import com.javaProject.startup.project.services.UserService;

public class DefaultUserService implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordService passService;

    @Override
    public UserData create(String username, Department department, Integer role) {
        if(username == null || username.isEmpty()) {
            return null;
        }

        if(department == null) {
            return null;
        }

        if(role == null) {
            return null;
        }

        if(userRepo.findByUsername(username).size() > 0) {
            return null;
        }

        UserData user = new UserData();
        user.setUsername(username);
        user.setDepartment(department);
        user.setRole(role);

        try {
            user.setPassword(passService.applyCryptography(username));
        } catch (BadHashConfigurationException ex) {
            return null;
        }

        return user;
    }

    @Override
    public Boolean updatePassword(Long id, String newPassword) {
        var user = userRepo.findById(id);

        if(!user.isPresent()) {
            return false;
        }

        if(!passService.verifyRules(newPassword)) {
            return false;
        }

        UserData actualUser = (UserData) user.get();
        try{
            actualUser.setPassword(passService.applyCryptography(newPassword));
        } catch (BadHashConfigurationException ex) {
            return null;
        }
        userRepo.save(actualUser);

        return true;
    }

    @Override
    public UserData get(String username) {
        throw new UnsupportedOperationException("Unimplemented method 'Get'");
    }
    
}
