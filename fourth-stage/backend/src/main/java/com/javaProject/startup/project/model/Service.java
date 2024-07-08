package com.javaProject.startup.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ServicesData")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "Name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "Intern")
    private Boolean intern;

    public Boolean getIntern() {
        return intern;
    }

    public void setIntern(Boolean intern) {
        this.intern = intern;
    }    

    @OneToOne()
    @JoinColumn(name="ManagerId", referencedColumnName="Id")
    public UserData manager;

    public UserData getManager() {
        return manager;
    }

    public void setManager(UserData managerId) {
        this.manager = managerId;
    }
}
