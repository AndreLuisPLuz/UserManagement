package com.trevis.startup.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trevis.startup.example.entities.DepartmentEntity;
import com.trevis.startup.example.exceptions.NotFoundException;
import com.trevis.startup.example.interfaces.DepartmentEntityService;
import com.trevis.startup.example.repositories.DepartmentRepository;


@Service
public class DepartamentServiceDefault implements DepartmentEntityService {

    @Autowired
    DepartmentRepository departamentRepository;   

    @Override
    public List<DepartmentEntity> get() {
        
        return departamentRepository.findAll(); 
    }

    @Override
    public DepartmentEntity get(Long id) {

        var deparment = departamentRepository.findById(id);
        if(deparment.isEmpty()) throw new NotFoundException();

        return deparment.get();
    }

}
