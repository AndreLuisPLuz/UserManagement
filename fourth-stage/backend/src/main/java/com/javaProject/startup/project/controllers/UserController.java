package com.javaProject.startup.project.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.javaProject.startup.project.dto.AuthToken;
import com.javaProject.startup.project.dto.JWTUserData;
import com.javaProject.startup.project.dto.RegisterUserData;
import com.javaProject.startup.project.model.UserData;
import com.javaProject.startup.project.repositories.DepartmentRepository;
import com.javaProject.startup.project.repositories.UserRepository;
import com.javaProject.startup.project.services.DepartmentService;
import com.javaProject.startup.project.services.JWTService;
import com.javaProject.startup.project.services.PasswordService;
import com.javaProject.startup.project.services.UserService;

@RestController
public class UserController {
    
    @Autowired
    PasswordService passwordService;
    
    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentRepository departmentRepository;
    
    @Autowired
    JWTService<JWTUserData> jwtService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user") 
    public ResponseEntity<AuthToken> registerUser(@RequestBody RegisterUserData obj, @RequestHeader("authorization") String jwt) {
        Map<String, Object> auth = jwtService.validate(jwt);
        if(auth == null) {
            return new ResponseEntity<AuthToken>(
                new AuthToken(
                    "Failed authentication :/",
                    null), 
                HttpStatus.UNAUTHORIZED);
        }

        if((Integer) auth.get("role") != 0){
            return new ResponseEntity<AuthToken>(
                new AuthToken(
                    "User does not have permission to execute such task :/",
                    null), 
                HttpStatus.FORBIDDEN);
        }

        var department = departmentRepository.findById(obj.department());
        if(!department.isPresent()) {
            return new ResponseEntity<AuthToken>(
                new AuthToken(
                    "Session expired :/",
                    null), 
                HttpStatus.BAD_REQUEST);
        }

        UserData user = userService.create(
            obj.login(), 
            department.get(),
            obj.role());

        if(user == null) {
            return new ResponseEntity<AuthToken>(
                new AuthToken(
                    "Invalid user data :/", 
                    null), 
                HttpStatus.BAD_REQUEST);
        }

        userRepository.save(user);

        var token = jwtService.getToken(new JWTUserData(user.getId(), user.getRole()));

        return new ResponseEntity<AuthToken>(new AuthToken(
            "User successfully created!",
            token), 
        HttpStatus.OK);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<String> updatePassword(@RequestBody String pass, @PathVariable Long id, @RequestHeader("authorization") String jwt) {
        Map<String, Object> auth = jwtService.validate(jwt);
        if(auth == null) {
            return new ResponseEntity<>(
                "Failed to authenticate :/", 
                HttpStatus.UNAUTHORIZED
            );
        }
        
        Integer jwtId = (Integer)auth.get("id");
        if(jwtId != id.intValue()) {
            return new ResponseEntity<>(
                "Failed to authenticate :/", 
                HttpStatus.FORBIDDEN
            );
        }

        var user = userRepository.findById(id);

        if (!user.isPresent()) {
            return new ResponseEntity<>(
                "User not found :/", 
                HttpStatus.NOT_FOUND
            );
        }

        if(!userService.updatePassword(id, pass)){
            return new ResponseEntity<>(
                "Failed to update password :/", 
                HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>(
                "Password updated successfully!", 
                HttpStatus.OK
            );
    }
}
