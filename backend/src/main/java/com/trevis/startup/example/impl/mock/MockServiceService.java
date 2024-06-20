package com.trevis.startup.example.impl.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.startup.example.dto.UserDTO;
import com.trevis.startup.example.model.Service;
import com.trevis.startup.example.model.User;
import com.trevis.startup.example.services.ServiceService;
import com.trevis.startup.example.structure.DataResult;

public class MockServiceService implements ServiceService{
    private List<Service> servicesBase;

    @Autowired
    MockDepartmentService mockDepartmentService;

    @Autowired
    MockUserService mockUserService;
    
    public MockServiceService() throws Exception {
        servicesBase =  new ArrayList<>();

        // // Get User
        // DataResult<UserDTO> getAllUserResult = mockUserService.Get("Yasmin Trembulack");
        
        // List<UserDTO> allUsers;
        // switch (getAllUserResult) {
        //     case DataResult.Ok<UserDTO> r -> allUsers = r.data();
        //     case DataResult.Error<UserDTO> error -> throw new Exception("Failed to fetch departments.");
        // }
        
        // UserDTO yas = allUsers.get(0);
        // var user = new User();
        // user.setName(yas.name());
        // user.setEmail(yas.email());

        
        
        var service1 = new Service();
        service1.setId(1l);
        service1.setName("Service 01");
        service1.setDescription("Service 01 description");
        service1.setIsInternal(true);
        
        service1.setManager(null);


    }

    @Override
    public DataResult<Service> Get(String query, Integer pageIndex, Integer pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Get'");
    }

}
