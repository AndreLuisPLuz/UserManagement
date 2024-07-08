package com.javaProject.startup.project.services;

import java.util.List;

import com.javaProject.startup.project.model.Service;

public interface ServiceService {
    List<Service> get(String query, Integer pageIndex, Integer pageSize);
}
