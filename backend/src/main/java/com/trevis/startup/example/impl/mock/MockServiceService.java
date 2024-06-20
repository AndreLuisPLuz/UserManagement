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
        var service = new Service();
        Integer departmentIndex = 0;

        for(Long i = 0l ; i < 20l; i++){
            if(departmentIndex == 2)
                departmentIndex = 0;

            String name = "Service "+i;
            String description = "Service "+i+" description.";
            service.setId(i);
            service.setName(name);
            service.setDescription(description);

            if(i % 2 == 0)
                service.setIsInternal(true);
            else
                service.setIsInternal(false);

            service.setManager(user);
            
            service.setDepartment(allDepartments.get(departmentIndex));

            departmentIndex++;
            
            servicesBase.add(service);
        }
    }

    @Override
    public DataResult<Service> Get(String query, Integer pageIndex, Integer pageSize) {
        if (pageIndex <= 0 || pageIndex == null)
            return new DataResult.Error<>(400, "pagination arguments cannot be equal to or less than zero.");

        if (pageSize == null || pageIndex == null) 
            return new DataResult.Error<>(400, "Pagination arguments required as query arguments.");
        
        
        List<Service> matchingServices = new ArrayList<>();

        for (var service : servicesBase) {
            if (service.getName().contains(query)) {
                matchingServices.add(service);
            }
        }

        Integer startIndex = (pageIndex - 1) * pageSize;
        Integer endIndex = pageIndex * pageSize;

        List<Service> servicesToReturn = new ArrayList<>();

        for (int i = startIndex; i <= endIndex; i++) {
            servicesToReturn.add(servicesBase.get(i));
        }

        if (servicesToReturn.size() == 0)
            return new DataResult.Error<>(404, "No matching services found.");

        return new DataResult.Ok<>("Matching services found.", servicesToReturn);

    }

}
