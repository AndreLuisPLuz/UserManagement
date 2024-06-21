package com.trevis.startup.example.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.example.model.Service;
import com.trevis.startup.example.services.ServiceService;

@RestController
public class ServiceController {
    @Autowired
    ServiceService service;
    
    @GetMapping("service")
    public List<Service> getQueryServices(String query, Integer pageIndex, Integer pageSize){
        try {
            return service.get(query, pageIndex, pageSize);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;


    }

}



