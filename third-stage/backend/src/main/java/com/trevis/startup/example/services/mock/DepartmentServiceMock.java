package com.trevis.startup.example.services.mock;

import java.util.ArrayList;
import java.util.List;

import com.trevis.startup.example.entities.DepartmentEntity;
import com.trevis.startup.example.interfaces.DepartmentEntityService;

import jakarta.annotation.PostConstruct;

public class DepartmentServiceMock implements DepartmentEntityService {

    private final List<DepartmentEntity> departaments = new ArrayList<>();

    public DepartmentServiceMock() {}

    @PostConstruct
    public void init()
    {
        DepartmentEntity depLOG = new DepartmentEntity();
        depLOG.setId(1l);
        depLOG.setName("Logistica");
        departaments.add(depLOG);
        
        DepartmentEntity depBSS = new DepartmentEntity();
        depBSS.setId(2l);
        depBSS.setName("BSS");
        departaments.add(depBSS);

        DepartmentEntity depETS = new DepartmentEntity();
        depETS.setId(3l);
        depETS.setName("ETS");
        departaments.add(depETS);
    }

    @Override
    public List<DepartmentEntity> get() {
        return departaments.stream().toList();
    }

    @Override
    public DepartmentEntity get(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
}
