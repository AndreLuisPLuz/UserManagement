package com.javaProject.startup.project.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.javaProject.startup.project.dto.JWTUserData;
import com.javaProject.startup.project.model.Department;
import com.javaProject.startup.project.repositories.DepartmentRepository;
import com.javaProject.startup.project.services.JWTService;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepo;

    @Autowired 
    JWTService<JWTUserData> jwtService;
    
    @GetMapping("/department")
    public ResponseEntity<List<Department>> getDepartments(@RequestHeader("Authorization") String jwt) {
        Map<String, Object> auth = jwtService.validate(jwt);
        if(auth == null) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
        }

        return new ResponseEntity<>(departmentRepo.findAll(), HttpStatus.OK);
    }
}
