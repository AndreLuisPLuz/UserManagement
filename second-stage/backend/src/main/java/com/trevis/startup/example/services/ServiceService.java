package com.trevis.startup.example.services;

import java.util.List;

import com.trevis.startup.example.exceptions.NoSuchServiceException;
import com.trevis.startup.example.model.Service;

public interface ServiceService {
    List<Service> get(String query, Integer pageIndex, Integer pageSize) throws NoSuchServiceException;
}
