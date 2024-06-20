package com.trevis.startup.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
public class Service extends BaseModel {
    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "description", columnDefinition = "VARCHAR(255)")
    private String description;

    @Column(name = "is_internal", columnDefinition = "BIT")
    private Boolean isInternal;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false, columnDefinition = "INTEGER")
    private User manager;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false, columnDefinition = "INTEGER")
    private Department department;

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

    public Boolean getIsInternal() {
        return isInternal;
    }

    public void setIsInternal(Boolean isInternal) {
        this.isInternal = isInternal;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    
}
