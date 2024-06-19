package com.trevis.startup.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.trevis.startup.example.impl.mock.MockDepartmentService;
import com.trevis.startup.example.services.DepartmentService;

@Configuration
public class DependenciesConfiguration {
    @Bean
    @Scope("singleton")
    protected DepartmentService departmentService() {
        return new MockDepartmentService();
    }
}