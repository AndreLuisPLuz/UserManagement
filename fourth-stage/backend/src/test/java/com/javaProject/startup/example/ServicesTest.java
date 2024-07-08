package com.javaProject.startup.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.javaProject.startup.project.impl.mock.MockServicesService;
import com.javaProject.startup.project.model.Service;
import com.javaProject.startup.project.services.ServiceService;

public class ServicesTest {
    
    @Autowired
	ServiceService services;
	
    String query = "example query";
    Integer pageIndex = 1;
    Integer pageSize = 10;


    @BeforeEach
    public void setUp() {
        services = new MockServicesService();
    }

    @Test
    public void testGet(){
        List<Service> result = services.get(query, pageIndex, pageSize);

        assertNotNull(result);
        assertTrue(result.size() <= pageSize);
    }
}
