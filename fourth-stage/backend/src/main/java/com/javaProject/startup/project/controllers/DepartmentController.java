package com.javaProject.startup.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.javaProject.startup.project.model.Department;
import com.javaProject.startup.project.repositories.DepartmentRepository;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepo;
    
    @GetMapping("/department")
    public ResponseEntity<List<Department>> getDepartments(@RequestHeader("Authorization") String jwt) {
        return new ResponseEntity<>(departmentRepo.findAll(), HttpStatus.OK);
    }
}
