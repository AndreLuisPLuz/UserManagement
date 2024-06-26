package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.entities.DepartmentEntity;
import com.trevis.startup.example.interfaces.DepartmentEntityService;

@SpringBootTest
public class DepartamentTests {
    
    @Autowired
    DepartmentEntityService departRepo;

    @Test
    void instanceTest(){
        assertTrue(departRepo.get().get(0) instanceof DepartmentEntity);
    }

    @Test
    void searchDepartaments() {
        List<DepartmentEntity> departaments = departRepo.get();

        DepartmentEntity depLOG = departaments.get(1);
        assertEquals(1l, depLOG.getId());
        assertEquals("Logistica", depLOG.getName());
    }
}
