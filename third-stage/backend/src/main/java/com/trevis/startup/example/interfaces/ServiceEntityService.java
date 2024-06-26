package com.trevis.startup.example.interfaces;

import java.util.List;

import com.trevis.startup.example.dto.requests.ServiceCreationDto;
import com.trevis.startup.example.entities.ServiceEntity;

public interface ServiceEntityService {
    ServiceEntity create(ServiceCreationDto service, Long managerId);
    List<ServiceEntity> get(String query, Integer pageIndex, Integer pageSize, Long requestUserId);
    ServiceEntity update(Long id, ServiceCreationDto service, Long userId);
    void deleteById(Long id, Long userId);
}
