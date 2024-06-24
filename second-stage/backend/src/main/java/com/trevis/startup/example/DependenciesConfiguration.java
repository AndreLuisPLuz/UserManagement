package com.trevis.startup.example;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.trevis.startup.example.impl.database.DefaultDepartmentService;
import com.trevis.startup.example.impl.database.DefaultServiceService;
import com.trevis.startup.example.impl.database.DefaultTypeService;
import com.trevis.startup.example.impl.database.DefaultUserService;
import com.trevis.startup.example.impl.mock.MockDepartmentService;
import com.trevis.startup.example.impl.mock.MockServiceService;
import com.trevis.startup.example.impl.mock.MockUserService;
import com.trevis.startup.example.impl.security.JwtAuthService;
import com.trevis.startup.example.impl.security.PBKDF2PasswordService;
import com.trevis.startup.example.services.AuthService;
import com.trevis.startup.example.services.DepartmentService;
import com.trevis.startup.example.services.PasswordService;
import com.trevis.startup.example.services.ServiceService;
import com.trevis.startup.example.services.UserService;
import com.trevis.startup.example.services.UserTypeService;

@Configuration
public class DependenciesConfiguration {

    @Bean
    @Scope
    public DepartmentService departmentService() {
        return new DefaultDepartmentService();
    }

    @Bean
    @Scope
    public UserTypeService userTypeService() {
        return new DefaultTypeService();
    }

    @Bean
    @Scope
    public UserService userService() {
        return new DefaultUserService();
    }

    @Bean
    @Scope
    public ServiceService serviceService() {
        return new DefaultServiceService();
    }

    

    @Bean
    @Scope
    public AuthService authService() {
        return new JwtAuthService();
    }

    @Bean
    @Scope
    public PasswordService passwordService() {
        return new PBKDF2PasswordService();
    }



}