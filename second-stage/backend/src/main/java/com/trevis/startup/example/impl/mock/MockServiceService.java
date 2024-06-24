package com.trevis.startup.example.impl.mock;

import java.util.ArrayList;
import java.util.List;

import com.trevis.startup.example.exceptions.NoSuchEntityException;
import com.trevis.startup.example.model.Service;
import com.trevis.startup.example.model.User;
import com.trevis.startup.example.services.ServiceService;

public class MockServiceService implements ServiceService{

    private static List<Service> services = new ArrayList<>();
    private Long helper_id;

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
        helper_id = 2l;
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

    @Override
    public Service create(String name, String description, Boolean internal, User menager) {
        helper_id += 1;
        Service newService = new Service();
        newService.setName(name);
        newService.setDescription(description);
        newService.setInternal(internal);
        newService.setManager(menager);
        newService.setId(helper_id);

        services.add(newService);
        return newService;
    }

    @Override
    public Service findById(Long id) throws NoSuchEntityException {
        for (Service service : services) {
            if(id == service.getId())
                return service;
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        for (Service service : services) {
            if(id == service.getId())
                services.remove(service);
        }
    }

    @Override
    public void save(Service service) {
        services.add(service);
    }
}
