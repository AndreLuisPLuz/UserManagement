package com.trevis.startup.example.impl.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.startup.example.model.Service;
import com.trevis.startup.example.repositories.ServiceJPARepository;
import com.trevis.startup.example.repositories.UserJPARepository;
import com.trevis.startup.example.services.ServiceService;

public class DefaultServiceService implements ServiceService{

    @Autowired
    ServiceJPARepository repo;


    @Override
    public List<Service> get(String query, Integer pageIndex, Integer pageSize) {

        if (pageIndex == null || pageSize == null) 
            return null; // "Pagination arguments cannot be equal to or less than zero."
        
        if (pageIndex < 1 || pageSize < 1) 
            return null; // "Pagination arguments required as query arguments."

            
        pageIndex *= pageSize;

        List<Service> services = repo.findByNameContaining(query);
        
        List<Service> pageServices = new ArrayList<>();

        if (services.size() <= pageSize) 
            return services;

        for(int i = pageIndex - pageSize; i < pageIndex; i ++){
            if (i == services.size()) 
                break;    
            pageServices.add(services.get(i));
        }

        return pageServices;
       
        
    }
    
}
