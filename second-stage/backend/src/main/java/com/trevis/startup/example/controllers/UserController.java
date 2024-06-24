package com.trevis.startup.example.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.example.dto.payload.PasswordChangePayload;
import com.trevis.startup.example.dto.payload.UserPayload;
import com.trevis.startup.example.dto.response.MessagesResponse;
import com.trevis.startup.example.exceptions.BadHashConfigurationException;
import com.trevis.startup.example.exceptions.NoSuchEntityException;
import com.trevis.startup.example.model.Department;
import com.trevis.startup.example.services.DepartmentService;
import com.trevis.startup.example.services.UserService;
import com.trevis.startup.example.services.UserTypeService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    UserTypeService typeService;

    @PostMapping("/api/user")
    public ResponseEntity<MessagesResponse> createUser(@RequestBody UserPayload payload) {
        var type = typeService.getById(payload.role().getId());
        Department department;

        try {
            department = departmentService.getById(payload.department());
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.notFound().build();
        }

        var savedUser = userService.create(
            payload.login(),
            department,
            type
        );
        
        List<String> messages = new ArrayList<>();
        
        if (savedUser == null) {
            messages.add("Could not create user.");
            return ResponseEntity.badRequest().body(new MessagesResponse(messages));
        }

        if (savedUser.getPassword() == null) {
            messages.add("error when setting default password");
            return ResponseEntity.badRequest().body(new MessagesResponse(messages));
        }
        
        messages.add("User created with success.");
        return ResponseEntity.ok().body(new MessagesResponse(messages));
    }
    
    @PatchMapping("/api/user")
    public ResponseEntity<MessagesResponse> updateUserPassword(
            @PathVariable Long id,
            @RequestBody PasswordChangePayload payload
    ) {
        Boolean changedSuccesfully;
        List<String> messages = new ArrayList<>();

        try {
            changedSuccesfully = userService.updatePassword(id, payload.password());
        } catch (NoSuchEntityException ex) {
            return ResponseEntity.notFound().build();
        } catch (BadHashConfigurationException ex) {
            messages.add("Bad hash configuration on the serve-side.");
            return ResponseEntity.internalServerError().body(new MessagesResponse(messages));
        }

        if (!changedSuccesfully) {
            messages.add("Password does not meet requirements.");
            return ResponseEntity.badRequest().body(new MessagesResponse(messages));
        }

        messages.add("Password changed with success.");
        return ResponseEntity.ok().body(new MessagesResponse(messages));
    }
}
