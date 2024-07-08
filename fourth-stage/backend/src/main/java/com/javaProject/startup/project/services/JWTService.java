package com.javaProject.startup.project.services;

import java.util.Map;

public interface JWTService<T> {
    String getToken(T obj);
    Map<String, Object> validate(String jwt);
}
