package com.javaProject.startup.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.javaProject.startup.project.dto.RegisterUserData;
import com.javaProject.startup.project.dto.UserDto;
import com.javaProject.startup.project.dto.payload.UpdatePasswordPayload;
import com.javaProject.startup.project.model.UserData;
import com.javaProject.startup.project.repositories.DepartmentRepository;
import com.javaProject.startup.project.repositories.UserRepository;
import com.javaProject.startup.project.services.AuthService;
import com.javaProject.startup.project.services.DepartmentService;
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
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user") 
    public ResponseEntity<?> registerUser(
            @RequestBody RegisterUserData obj,
            @RequestHeader("authorization") String jwt
    ) {
        var department = departmentRepository.findById(obj.department());
        if(!department.isPresent()) {
            return ResponseEntity.status(404).body("Department not found.");
        }

        UserData user = userService.create(
            obj.login(), 
            department.get(),
            obj.role());

        if(user == null) {
            return ResponseEntity.status(400).body("Couldn't build user.");
        }

        var savedUser = userRepository.save(user);

        return ResponseEntity.ok().body(
            UserDto.buildFromEntity(savedUser)
        );
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<String> updatePassword(
            @RequestBody UpdatePasswordPayload payload,
            @PathVariable Long id,
            @RequestHeader("authorization") String jwt
    ) {
        var user = userRepository.findById(id);

        if (!user.isPresent()) {
            return new ResponseEntity<>(
                "User not found :/", 
                HttpStatus.NOT_FOUND
            );
        }

        if(!userService.updatePassword(id, payload.password())){
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
