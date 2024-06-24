package com.trevis.startup.example.services;

import java.util.List;
import java.util.Optional;

import com.trevis.startup.example.exceptions.NoSuchEntityException;
import com.trevis.startup.example.exceptions.NoSuchServiceException;
import com.trevis.startup.example.model.Service;
import com.trevis.startup.example.model.User;

public interface ServiceService {
    List<Service> get(String query, Integer pageIndex, Integer pageSize) throws NoSuchServiceException;
    Service create(String name, String description, Boolean internal, User menager);
    Optional<Service> findById(Long id) throws NoSuchEntityException;
    void deleteById(Long id);
}
