package com.trevis.startup.example.dto.response;

import com.trevis.startup.example.entities.UserEntity;
import com.trevis.startup.example.enums.UserEntityRole;

public class UserReturnDto {
    
    public String username;
    public Integer role;
    public Long id;
    public Long departmentId;

    public UserReturnDto(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = UserEntityRole.fromRole(user.getRole());
        this.departmentId = user.getDepartment().getId();
    }
}
