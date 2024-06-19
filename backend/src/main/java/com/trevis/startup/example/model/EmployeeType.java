package com.trevis.startup.example.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

public class EmployeeType extends BaseModel {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "employeeType")
    private Set<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    
}
