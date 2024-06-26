package com.trevis.startup.example.enums;

import com.trevis.startup.example.exceptions.ResponseException;

public enum UserEntityRole {
    ADMINISTRATOR,
    MANAGER,
    COLLABORATOR;

    public static UserEntityRole fromInteger(int x) {
        switch(x) {
            case 0:
                return ADMINISTRATOR;
            case 1:
                return MANAGER;
            case 2: 
                return COLLABORATOR;
            default:
                throw new ResponseException("Role not compatible", 400);
        }
    }

    public static Integer fromRole(UserEntityRole role) {
        switch(role) {
            case ADMINISTRATOR:
                return 0;
            case MANAGER:
                return 1;
            case COLLABORATOR: 
                return 2;
            default:
                throw new ResponseException("Something went wrong", 501);
        }
    }
}