package com.trevis.startup.example.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

public class EmployeeType extends BaseModel {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "employeeType")
    private Set<User> users;
}
