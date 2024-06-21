package com.trevis.startup.example.impl;

import java.util.ArrayList;
import java.util.List;

import com.trevis.startup.example.model.Service;
import com.trevis.startup.example.services.ServiceService;

public class MockServiceService implements ServiceService{

    private static List<Service> services = new ArrayList<>();

    public MockServiceService(){
        var newService = new Service();
        newService.setId(1l);
        newService.setName("Service1");
        newService.setDescription("First service");

        services.add(newService);

        newService = new Service();
        newService.setId(2l);
        newService.setName("Service2");
        newService.setDescription("Second service");

        services.add(newService);
    }

    @Override
    public List<Service> get(String query, Integer pageIndex, Integer pageSize){
        if (query != null) {

            pageIndex *= pageSize;

            var filteredServices = services.stream().filter(u -> u.getName().contains(query)).toList();

            List<Service> pageServices = new ArrayList<>();

            for(int i = pageIndex - pageSize; i < pageIndex; i ++){
                pageServices.add(filteredServices.get(i));
            }

            return pageServices;
        } else {
            List<Service> pageServices = new ArrayList<>();

            for(int i = pageIndex - pageSize; i < pageIndex; i ++){
                pageServices.add(services.get(i));
            }

            return pageServices;
        }
    }
}
