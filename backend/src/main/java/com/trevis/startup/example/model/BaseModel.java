package com.trevis.startup.example.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "INTEGER")
    protected Long id;

    @Column(name = "createdAt", columnDefinition = "DATETIME")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "updatedAt", columnDefinition = "DATETIME")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    public BaseModel() {
        createdAt = new Date();
        updatedAt = null;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
