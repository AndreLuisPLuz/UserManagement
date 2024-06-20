package com.trevis.startup.example.impl.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.startup.example.model.Department;
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

        // Get User
        DataResult<User> getAllUserResult = mockUserService.Get("Yasmin Trembulack");
        
        List<User> allUsers;
        switch (getAllUserResult) {
            case DataResult.Ok<User> r -> allUsers = r.data();
            case DataResult.Error<User> error -> throw new Exception("Failed to fetch departments.");
        }
        User user = allUsers.get(0);

        //Get Departments
         DataResult<Department> getAllDepartmentsResult = mockDepartmentService.GetAll();
        List<Department> allDepartments;

        switch (getAllDepartmentsResult) {
            case DataResult.Ok<Department> r -> { allDepartments = r.data(); }
            case DataResult.Error<Department> error -> { throw new Exception("Failed to fetch departments."); }
        };
        

        // Set Services
        var service1 = new Service();
        service1.setId(1l);
        service1.setName("Service 01");
        service1.setDescription("Service 01 description");
        service1.setIsInternal(true);
        service1.setManager(user);
        service1.setDepartment(allDepartments.get(1));

        var service2 = new Service();
        service2.setId(1l);
        service2.setName("Service 02");
        service2.setDescription("Service 02 description");
        service2.setIsInternal(true);
        service2.setManager(user);
        service2.setDepartment(allDepartments.get(0));

        var service3 = new Service();
        service3.setId(1l);
        service3.setName("Service 03");
        service3.setDescription("Service 03 description");
        service3.setIsInternal(false);
        service3.setManager(user);
        service3.setDepartment(allDepartments.get(1));

        servicesBase.add(service1);
        servicesBase.add(service2);
        servicesBase.add(service3);
    }

    @Override
    public DataResult<Service> Get(String query, Integer pageIndex, Integer pageSize) {
        List<Service> matchingServices = new ArrayList<>();

        for (Service s : servicesBase) {
            if (s.getName().contains(query)){
                matchingServices.add(s);
            }
        }
        if (matchingServices.size() == 0)
            return new DataResult.Error<>(404, "No matching services found.");

        return new DataResult.Ok<>("Matching services found.", matchingServices);

    }

}
