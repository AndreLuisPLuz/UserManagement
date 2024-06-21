package com.trevis.startup.example.model;

public enum RoleEnum {
    Collaborator(0l),
    Administrator(1l),
    Manager(2l);

    private Long id;

    private RoleEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
