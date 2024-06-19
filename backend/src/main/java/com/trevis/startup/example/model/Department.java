package com.trevis.startup.example.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department extends BaseModel {
    @Column(name = "name")
    private String name;

    @Column(name = "acronym")
    private String acronym;

    @OneToMany(mappedBy = "department")
    private Set<User> users;

    @OneToMany(mappedBy = "department")
    private Set<Service> services;
}
