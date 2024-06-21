package com.trevis.startup.example.services;

import java.util.Optional;

import com.trevis.startup.example.exceptions.NoSuchEntityException;
import com.trevis.startup.example.model.Department;
import com.trevis.startup.example.model.User;
import com.trevis.startup.example.model.UserType;

public interface UserService {
    User create(String username, Department department, UserType type);
    Boolean updatePassword(Long id, String newPassword) throws NoSuchEntityException;
    User get(String username) throws NoSuchEntityException;
    Optional<User> findById(Long id) throws NoSuchEntityException;
}
