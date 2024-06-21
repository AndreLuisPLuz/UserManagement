package com.trevis.startup.example.services;

import com.trevis.startup.example.exceptions.NoSuchUserException;
import com.trevis.startup.example.model.Department;
import com.trevis.startup.example.model.User;
import com.trevis.startup.example.model.UserType;

public interface UserService {
    User create(String username, Department department, UserType type);
    Boolean updatePassword(Long id, String newPassword) throws NoSuchUserException;
    User get(String username) throws NoSuchUserException;
}
