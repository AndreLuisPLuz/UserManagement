package com.javaProject.startup.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.javaProject.startup.project.impl.DefaultAuthService;
import com.javaProject.startup.project.impl.DefaultDepartmentService;
import com.javaProject.startup.project.impl.DefaultJWTService;
import com.javaProject.startup.project.impl.DefaultPasswordService;
import com.javaProject.startup.project.impl.DefaultServiceService;
import com.javaProject.startup.project.impl.DefaultUserService;
import com.javaProject.startup.project.impl.RS256SignatureService;
import com.javaProject.startup.project.impl.RSAKeyService;
import com.javaProject.startup.project.impl.SHA256HashService;
import com.javaProject.startup.project.impl.SHAPasswordEncoder;
import com.javaProject.startup.project.impl.mock.MockDepartmentService;
import com.javaProject.startup.project.impl.mock.MockServicesService;
import com.javaProject.startup.project.services.AuthService;
import com.javaProject.startup.project.services.DepartmentService;
import com.javaProject.startup.project.services.HashService;
import com.javaProject.startup.project.services.JWTService;
import com.javaProject.startup.project.services.KeyService;
import com.javaProject.startup.project.services.PasswordService;
import com.javaProject.startup.project.services.ServiceService;
import com.javaProject.startup.project.services.SignatureService;
import com.javaProject.startup.project.services.UserService;

@Configuration
public class DependenciesConfiguration {

    @Bean
    DepartmentService departamentService(){
        return new DefaultDepartmentService();
    }

    @Bean
    UserService userService(){
        return new DefaultUserService();
    }

    @Bean
    ServiceService serviceService(){
        return new DefaultServiceService();
    }

    @Bean
    PasswordService passwordService(){
        return new DefaultPasswordService();
    }

    @Bean
    AuthService authService(){
        return new DefaultAuthService();
    }

    @Bean
    JWTService jwtService(){
        return new DefaultJWTService<>();
    }

    @Bean
    KeyService keyService(){
        return new RSAKeyService();
    }

    @Bean
    HashService hashService(){
        return new SHA256HashService();
    }

    @Bean
    SignatureService signatureService(){
        return new RS256SignatureService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new SHAPasswordEncoder();
    }

    // @Bean
    // MockUserService mockUserService() {
    //     return new MockUserService();
    // }

    // @Bean
    // MockDepartmentService mockDepartmentService(){
    //     return new MockDepartmentService();
    // }

    // @Bean
    // MockServicesService mockServicesService(){
    //     return new MockServicesService();
    // }
}