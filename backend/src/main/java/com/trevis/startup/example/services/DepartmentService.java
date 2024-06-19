package com.trevis.startup.example.services;

import com.trevis.startup.example.dto.DepartmentDTO;
import com.trevis.startup.example.structure.DataResult;

public interface DepartmentService {
    DataResult<DepartmentDTO> GetAll();
}
