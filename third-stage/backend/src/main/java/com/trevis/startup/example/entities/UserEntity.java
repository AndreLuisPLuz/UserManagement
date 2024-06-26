package com.trevis.startup.example.entities;

import java.util.List;

import com.trevis.startup.example.enums.UserEntityRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity @Table(name = "UserData")
public class UserEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Username", unique = true)
    private String username;

    @Column(name = "Password")
    private String password;

    @Enumerated(EnumType.STRING) @Column(name = "Role")
    private UserEntityRole role;

    @ManyToOne @JoinColumn(name = "Department_id")
    private DepartmentEntity department;

    @OneToMany(mappedBy = "manager")
    private List<ServiceEntity> services;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntityRole getRole() {
        return role;
    }
    public void setRole(UserEntityRole role) {
        this.role = role;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }
    public void setDepartment(DepartmentEntity departament) {
        this.department = departament;
    }

    public List<ServiceEntity> getServices() {
        return services;
    }
    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }
}
