package com.javaProject.startup.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.annotation.RequestScope;

import com.javaProject.startup.project.dto.JWTUserData;
import com.javaProject.startup.project.impl.DefaultAuthService;
import com.javaProject.startup.project.impl.DefaultDepartmentService;
import com.javaProject.startup.project.impl.DefaultJWTService;
import com.javaProject.startup.project.impl.DefaultPasswordService;
import com.javaProject.startup.project.impl.DefaultServiceService;
import com.javaProject.startup.project.impl.DefaultUserService;
import com.javaProject.startup.project.impl.PBKDF2PasswordService;
import com.javaProject.startup.project.impl.RS256SignatureService;
import com.javaProject.startup.project.impl.RSAKeyService;
import com.javaProject.startup.project.impl.SHA256HashService;
import com.javaProject.startup.project.impl.SHAPasswordEncoder;
// import com.javaProject.startup.project.impl.mock.MockDepartmentService;
// import com.javaProject.startup.project.impl.mock.MockServicesService;
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
        return new DefaultAuthService();
    }

    @Bean
    @RequestScope
    JWTService<JWTUserData> jwtService(){
        return new DefaultJWTService<>();
    }

    @Bean
    @Scope("singleton")
    KeyService keyService(){
        return new RSAKeyService();
    }

    @Bean
    @Scope("singleton")
    HashService hashService(){
        return new SHA256HashService();
    }

    @Bean
    @Scope("singleton")
    SignatureService signatureService(){
        return new RS256SignatureService();
    }

    @Bean
    @Scope("singleton")
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