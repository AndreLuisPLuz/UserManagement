package com.trevis.startup.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity @Table(name = "Service")
public class ServiceEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;
    
    @Column(name = "Internal")
    private boolean intern;

    @ManyToOne @JoinColumn(name = "User_id")
    private UserEntity manager;

    @ManyToOne @JoinColumn(name = "Departament_id")
    private DepartmentEntity department;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIntern() {
        return intern;
    }
    public void setIntern(boolean internal) {
        this.intern = internal;
    }

    public void setDepartment(DepartmentEntity departament) {
        this.department = departament;
    }
    public DepartmentEntity getDepartment() {
        return department;
    }

    public UserEntity getManager() {
        return manager;
    }
    public void setManager(UserEntity manager) {
        this.manager = manager;
    }
}
