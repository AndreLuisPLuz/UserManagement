package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trevis.startup.example.services.ServiceService;

@SpringBootTest
class ServiceServiceTests {
    
    @Autowired
    ServiceService service;

    @Test
    void serviceServiceTest(){
        assertEquals(service.get(null, 1, 2), 2);
    }
}
