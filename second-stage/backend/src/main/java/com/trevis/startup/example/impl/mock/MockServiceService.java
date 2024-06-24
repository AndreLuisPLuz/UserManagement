package com.trevis.startup.example.impl.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.trevis.startup.example.exceptions.NoSuchEntityException;
import com.trevis.startup.example.model.Service;
import com.trevis.startup.example.model.User;
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

    @Override
    public Service create(String name, String description, Boolean internal, User menager) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Optional<Service> findById(Long id) throws NoSuchEntityException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public void save(Service service) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}
