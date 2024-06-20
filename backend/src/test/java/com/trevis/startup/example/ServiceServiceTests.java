package com.trevis.startup.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.trevis.startup.example.model.Service;
import com.trevis.startup.example.services.ServiceService;
import com.trevis.startup.example.structure.DataResult;

@SpringBootTest
public class ServiceServiceTests {

    @Autowired
    ServiceService service;

    @Test
    private void serviceServiceGetTest(){
        var result = service.Get("", 2, 5);

        Boolean wasGetSuccessfully;
        List<Service> services;
        
        switch (result) {
            case DataResult.Ok<Service> r -> {
                wasGetSuccessfully = true;
                services = r.data();
            }
            case DataResult.Error<Service> error -> {
                wasGetSuccessfully = false;
                services = null;
            }
        };

        assertNotNull(services);
        assertTrue(wasGetSuccessfully);


        result = service.Get("", null, null);

        switch (result) {
            case DataResult.Ok<Service> r -> {
                wasGetSuccessfully = true;
                services = r.data();
            }
            case DataResult.Error<Service> error -> {
                wasGetSuccessfully = false;
                services = null;
            }
        };

        assertNull(services);
        assertFalse(wasGetSuccessfully);

        result = service.Get("", -2, -5);

        switch (result) {
            case DataResult.Ok<Service> r -> {
                wasGetSuccessfully = true;
                services = r.data();
            }
            case DataResult.Error<Service> error -> {
                wasGetSuccessfully = false;
                services = null;
            }
        };

        assertNull(services);
        assertFalse(wasGetSuccessfully);
    }


    
}
