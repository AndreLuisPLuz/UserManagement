package com.trevis.startup.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.trevis.startup.example.interfaces.AuthService;
import com.trevis.startup.example.interfaces.DepartmentEntityService;
import com.trevis.startup.example.interfaces.PasswordService;
import com.trevis.startup.example.interfaces.ServiceEntityService;
import com.trevis.startup.example.interfaces.UserEntityService;
import com.trevis.startup.example.services.AuthServiceDefault;
import com.trevis.startup.example.services.DepartamentServiceDefault;
import com.trevis.startup.example.services.PasswordServiceDefault;
import com.trevis.startup.example.services.ServiceServiceDefault;
import com.trevis.startup.example.services.UserServiceDefault;

@Configuration
public class DependenciesConfiguration {

    @Bean @Scope("singleton") @Primary
    public UserEntityService userService(){
        return new UserServiceDefault();
    }

    @Bean @Scope("singleton") @Primary
    public ServiceEntityService serviceService(){
        return new ServiceServiceDefault();
    }

    @Bean @Scope("singleton") @Primary
    public DepartmentEntityService departmentService(){
        return new DepartamentServiceDefault();
    }

    @Bean @Scope("singleton") @Primary
    public AuthService authService() {
        return new AuthServiceDefault();
    }

    @Bean @Scope("singleton") @Primary
    public PasswordService passwordService() {
        return new PasswordServiceDefault();
    }
}