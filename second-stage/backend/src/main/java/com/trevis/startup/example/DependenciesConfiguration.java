package com.trevis.startup.example;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.trevis.startup.example.impl.database.DefaultDepartmentService;
import com.trevis.startup.example.impl.database.DefaultServiceService;
import com.trevis.startup.example.impl.database.DefaultUserService;
import com.trevis.startup.example.services.AuthService;
import com.trevis.startup.example.services.DepartmentService;
import com.trevis.startup.example.services.PasswordService;
import com.trevis.startup.example.services.ServiceService;
import com.trevis.startup.example.services.UserService;

@Configuration
public class DependenciesConfiguration {

    @Bean
    @Scope
    public DepartmentService departmentService() {
        return new DefaultDepartmentService();
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
        return null;
    }

    @Bean
    @Scope
    public PasswordService passwordService() {
        return null;
    }



}