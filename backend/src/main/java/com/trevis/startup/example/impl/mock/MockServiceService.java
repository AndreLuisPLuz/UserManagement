package com.trevis.startup.example.impl.mock;

import java.util.ArrayList;
import java.util.List;
import com.trevis.startup.example.model.Service;
import com.trevis.startup.example.services.ServiceService;

public class MockServiceService implements ServiceService{
    private List<Service> servicesBase;
    
    public MockServiceService(){
        servicesBase =  new ArrayList<>();
    }

    public void Get(String query,Integer pageIndex, Integer pageSize){
        
    }
}
