package com.trevis.startup.example.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.example.dto.response.DataResponse;
import com.trevis.startup.example.model.Department;
import com.trevis.startup.example.services.DepartmentService;

@RestController
public class ServiceDepartment {
    
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/api/department")
    public ResponseEntity<DataResponse<Department>> getAllDepartments(){
        List<Department> allDepartments = departmentService.getAll();

    }
}
