package com.javaProject.startup.project.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.javaProject.startup.project.model.Service;
import com.javaProject.startup.project.repositories.ServiceRepository;
import com.javaProject.startup.project.services.ServiceService;

public class DefaultServiceService implements ServiceService {

    @Autowired
    ServiceRepository serviceRepo;

    @Override
    public List<Service> get(String query, Integer pageIndex, Integer pageSize) {
        return paginate(pageIndex, pageSize, filter(query));
    }

    public List<Service> filter(String query) {
        List<Service> services = serviceRepo.findAll();

        if(query == null || query.isEmpty()) {
            return services;
        }

        return services.stream()
            .filter(s -> s.getName().contains(query))
            .toList();
    }
    
    public List<Service> paginate(Integer pageIndex, Integer pageSize, List<Service> filteredServices) {
        if(filteredServices.size() < pageSize) {
            return filteredServices;
        }
        
        if(pageIndex <= 0) {
            pageIndex = 1;
        }

        var qty = pageIndex * pageSize;
        List<Service> services = new ArrayList<>();
        for(int i = (qty - pageSize); i < qty; i++) {
            services.add(filteredServices.get(i));
        }

        return services;
    }
}
