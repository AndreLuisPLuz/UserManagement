package com.trevis.startup.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.trevis.startup.example.impl.mock.MockDepartmentService;
import com.trevis.startup.example.impl.mock.MockUserService;
import com.trevis.startup.example.services.DepartmentService;
import com.trevis.startup.example.services.UserService;

@Configuration
public class DependenciesConfiguration {
    @Bean
    @Scope("singleton")
    protected DepartmentService departmentService() {
        return new MockDepartmentService();
    }

    @Bean
    @Scope("singleton")
    protected UserService userService() throws Exception {
        return new MockUserService();
    }
}