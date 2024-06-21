package com.trevis.startup.example.services;

import com.trevis.startup.example.model.Department;
import com.trevis.startup.example.model.User;
import com.trevis.startup.example.model.UserType;

public interface UserService {
    User create(String username, Department department, UserType type);
    Void updatePassword(Long id, String newPassword);
    User get(String username);
}
