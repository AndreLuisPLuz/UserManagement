package com.trevis.startup.example.dto.response;

import com.trevis.startup.example.entities.ServiceEntity;

public class ServiceResponseDto {

    public Long id;
    public String name; 
    public String description;
    public Boolean intern;
    public Long departmentId;
    public Long managerId;

    public ServiceResponseDto(ServiceEntity service) {
        this.id = service.getId();
        this.name = service.getName(); 
        this.description = service.getDescription();
        this.intern = service.getIntern();
        this.departmentId = service.getDepartment().getId();
        this.managerId = service.getManager().getId();
    }
}
