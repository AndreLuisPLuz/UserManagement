package com.trevis.startup.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.trevis.startup.example.dto.requests.ServiceCreationDto;
import com.trevis.startup.example.entities.ServiceEntity;
import com.trevis.startup.example.entities.UserEntity;
import com.trevis.startup.example.enums.UserEntityRole;
import com.trevis.startup.example.exceptions.ForbiddenException;
import com.trevis.startup.example.exceptions.NotFoundException;
import com.trevis.startup.example.interfaces.ServiceEntityService;
import com.trevis.startup.example.interfaces.UserEntityService;
import com.trevis.startup.example.repositories.ServiceRepository;

public class ServiceServiceDefault implements ServiceEntityService {

    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    UserEntityService userEntityService;


    @Override
    public ServiceEntity create(ServiceCreationDto payload, Long requestUserId) {
        
        var requestUser = userEntityService.get(requestUserId);

        if(requestUser.getRole() != UserEntityRole.ADMINISTRATOR) throw new ForbiddenException();

        ServiceEntity newService = new ServiceEntity();
        newService.setDepartment(requestUser.getDepartment());
        newService.setManager(requestUser);
        newService.setDescription(payload.description());
        newService.setIntern(payload.intern());
        newService.setName(payload.name());

        return serviceRepository.save(newService);
    }

    @Override
    public List<ServiceEntity> get(String query, Integer pageIndex, Integer pageSize, Long requestUserId) {
        
        List<ServiceEntity> data = serviceRepository.findByName(query, PageRequest.of(pageIndex-1, pageSize));
        UserEntity requestUser = userEntityService.get(requestUserId);
        
        return data
            .stream()
            .filter(x -> x.getIntern() ? (x.getManager().getDepartment().getId() == requestUser.getDepartment().getId()) : true)
            .toList();
    }

    @Override
    public ServiceEntity update(Long id, ServiceCreationDto payload, Long userId) {

        var query = serviceRepository.findById(id);

        if(query.isEmpty()) throw new NotFoundException();
        if(query.get().getManager().getId() != userId) throw new ForbiddenException();

        ServiceEntity service = query.get();
        service.setDescription(payload.description());
        service.setIntern(payload.intern());
        service.setName(payload.name());

        return serviceRepository.save(service);
    }

    @Override
    public void deleteById(Long id, Long userId) {

        var query = serviceRepository.findById(id);

        if(query.isEmpty()) throw new NotFoundException();
        if(query.get().getManager().getId() != userId) throw new ForbiddenException();

        serviceRepository.deleteById(id);
    }
}
