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

    // Sigla do departamento
    @Column(name = "acronym")
    private String acronym;

    @OneToMany(mappedBy = "department")
    private Set<User> users;

    @OneToMany(mappedBy = "department")
    private Set<Service> services;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
}
