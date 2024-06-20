package com.trevis.startup.example.services;

import com.trevis.startup.example.model.Service;
import com.trevis.startup.example.structure.DataResult;

public interface ServiceService {
    DataResult<Service> Get(String query, Integer pageIndex, Integer pageSize);
}
