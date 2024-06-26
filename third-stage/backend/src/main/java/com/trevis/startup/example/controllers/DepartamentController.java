package com.trevis.startup.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.example.entities.DepartmentEntity;
import com.trevis.startup.example.interfaces.DepartmentEntityService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/department")
@RestController
public class DepartamentController {

    @Autowired
    DepartmentEntityService departamentService;

    @GetMapping("")
    public ResponseEntity<List<DepartmentEntity>> getAll(@RequestParam String param) {
        return ResponseEntity.ok(departamentService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentEntity> get(@PathVariable Long id) {
        return ResponseEntity.ok(departamentService.get(id));
    }
}
