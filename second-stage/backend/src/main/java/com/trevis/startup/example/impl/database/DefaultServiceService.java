package com.trevis.startup.example.impl.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.startup.example.exceptions.NoSuchEntityException;
import com.trevis.startup.example.exceptions.NoSuchServiceException;
import com.trevis.startup.example.model.Service;
import com.trevis.startup.example.model.User;
import com.trevis.startup.example.repositories.ServiceJPARepository;
import com.trevis.startup.example.services.ServiceService;

public class DefaultServiceService implements ServiceService{

    @Autowired
    ServiceJPARepository repo;

    @Override
    public List<Service> get(String query, Integer pageIndex, Integer pageSize) throws NoSuchServiceException{

        if (pageIndex == null || pageSize == null) 
            throw new NoSuchServiceException("Pagination arguments cannot be equal to or less than zero.");
        
        if (pageIndex < 1 || pageSize < 1) 
            throw new NoSuchServiceException("Pagination arguments required as query arguments.");

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

    @Override
    public Service create(String name, String description, Boolean internal, User menager) {
        var newService = new Service();
        newService.setName(name);
        newService.setDescription(description);
        newService.setInternal(internal);
        newService.setManager(menager);
        return repo.save(newService);
    }

    @Override
    public Service findById(Long id) throws NoSuchEntityException {
        var serviceFetch = repo.findById(id);
        if (!serviceFetch.isPresent())
            throw new NoSuchEntityException("Service not found.");

        return serviceFetch.get();
    }

    @Override
    public void deleteById(Long id) {
        deleteById(id);
    }

    @Override
    public void save(Service entity) {
        repo.save(entity);
    }

    
    
}
