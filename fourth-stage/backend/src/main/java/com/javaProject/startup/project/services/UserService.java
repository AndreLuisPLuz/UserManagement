package com.javaProject.startup.project.services;

import com.javaProject.startup.project.model.Department;
import com.javaProject.startup.project.model.UserData;

public interface UserService {
    UserData create(String username, Department department, Integer role);
    Boolean updatePassword(Long id, String newPassword);
    UserData get(String username);
}
