package com.javaProject.startup.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.javaProject.startup.project.filters.AuthFilter;
import com.javaProject.startup.project.impl.Auth0JwtService;
import com.javaProject.startup.project.impl.DefaultDepartmentService;
import com.javaProject.startup.project.impl.DefaultServiceService;
import com.javaProject.startup.project.impl.DefaultUserService;
import com.javaProject.startup.project.impl.PBKDF2PasswordService;
import com.javaProject.startup.project.services.AuthService;
import com.javaProject.startup.project.services.DepartmentService;
import com.javaProject.startup.project.services.PasswordService;
import com.javaProject.startup.project.services.ServiceService;
import com.javaProject.startup.project.services.UserService;
import com.javaProject.startup.project.sessions.UserSession;

@Configuration
public class DependenciesConfiguration {

    @Bean
    @Scope("singleton")
    DepartmentService departamentService(){
        return new DefaultDepartmentService();
    }

    @Bean
    @Scope("singleton")
    UserService userService(){
        return new DefaultUserService();
    }

    @Bean
    @Scope("singleton")
    ServiceService serviceService(){
        return new DefaultServiceService();
    }

    @Bean
    @Scope("singleton")
    PasswordService passwordService(){
        return new PBKDF2PasswordService();
    }

    @Bean
    @Scope("singleton")
    AuthService authService(){
        return new Auth0JwtService();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    protected UserSession userSession() {
        return new UserSession();
    }

    @Bean
    @Scope("singleton")
    protected AuthFilter authFilter() {
        return new AuthFilter();
    }
}