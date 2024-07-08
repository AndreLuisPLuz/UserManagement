package com.javaProject.startup.project.impl.mock;

import java.util.*;
import com.javaProject.startup.project.model.Service;
import com.javaProject.startup.project.services.ServiceService;

// Implementação de um serviço fictício para serviços
public class MockServicesService implements ServiceService {
    
    // Método da interface ServiceService para obter serviços com base em parâmetros
    @Override
    public List<Service> get(String query, Integer pageIndex, Integer pageSize) {
        List<Service> services = new ArrayList<>();
        
        // Criação de um objeto de serviço fictício
        var service = new Service();
        service.setId(1L);
        service.setDescription("aaaaaaaaaa");
        service.setIntern(true);
        services.add(service);
        
        return services; // Retorna a lista contendo o serviço fictício
    }
}