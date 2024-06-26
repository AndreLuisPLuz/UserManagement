package com.trevis.startup.example.interfaces;

import java.util.List;

import com.trevis.startup.example.entities.DepartmentEntity;

public interface DepartmentEntityService {
    List<DepartmentEntity> get();
    DepartmentEntity get(Long id);
}
