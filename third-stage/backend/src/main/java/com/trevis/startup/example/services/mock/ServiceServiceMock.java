package com.trevis.startup.example.services.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.startup.example.dto.requests.ServiceCreationDto;
import com.trevis.startup.example.entities.ServiceEntity;
import com.trevis.startup.example.interfaces.DepartmentEntityService;
import com.trevis.startup.example.interfaces.ServiceEntityService;
import com.trevis.startup.example.interfaces.UserEntityService;

import jakarta.annotation.PostConstruct;

public class ServiceServiceMock implements ServiceEntityService {

    @Autowired
    DepartmentEntityService departRepo;

    @Autowired
    UserEntityService userRepo;

    
    private final List<ServiceEntity> services = new ArrayList<>();

    public ServiceServiceMock () {}

    @PostConstruct
    public void init()
    {
        ServiceEntity service1 = new ServiceEntity();
        service1.setId(1l);
        service1.setName("Fazer Power BI");
        service1.setDescription("Dashboards e tratamentos de dados");
        service1.setIntern(false);
        service1.setDepartment(departRepo.get().get(2)); 
        service1.setManager(userRepo.get("Zago"));
        services.add(service1);

        ServiceEntity service2 = new ServiceEntity();
        service2.setId(2l);
        service2.setName("Fazer códigos");
        service2.setDescription("Desenvolver sistema em Python");
        service2.setIntern(true);
        service2.setDepartment(departRepo.get().get(1)); 
        service2.setManager(userRepo.get("Silva"));
        services.add(service2);

        ServiceEntity service3 = new ServiceEntity();
        service3.setId(3l);
        service3.setName("Fazer códigos");
        service3.setDescription("Desenvolver sistema em BrainFuck");
        service3.setIntern(false);
        service3.setDepartment(departRepo.get().get(3)); 
        service3.setManager(userRepo.get("Duda"));
        services.add(service3);
    }

    @Override
    public List<ServiceEntity> get(String query, Integer pageIndex, Integer pageSize, Long requestUserId) {
        
        List<ServiceEntity> filteredServices = services.stream()
        .filter(u -> u.getName().contains(query))
        .toList();

        int start = pageIndex * pageSize;
        int end = Math.min(start + pageSize, filteredServices.size());

        if (start >= filteredServices.size()) {
            return new ArrayList<>();
        }

        return filteredServices.subList(start, end);
    }

    @Override
    public ServiceEntity create(ServiceCreationDto service, Long managerId) {
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ServiceEntity update(Long id, ServiceCreationDto service, Long userId) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteById(Long id, Long userId) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    
}
