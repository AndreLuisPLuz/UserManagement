package com.trevis.startup.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trevis.startup.example.dto.requests.UserCreationDto;
import com.trevis.startup.example.dto.requests.UserUpdateDto;
import com.trevis.startup.example.entities.UserEntity;
import com.trevis.startup.example.enums.UserEntityRole;
import com.trevis.startup.example.exceptions.ForbiddenException;
import com.trevis.startup.example.exceptions.NotFoundException;
import com.trevis.startup.example.exceptions.UnauthorizedException;
import com.trevis.startup.example.interfaces.DepartmentEntityService;
import com.trevis.startup.example.interfaces.PasswordService;
import com.trevis.startup.example.interfaces.UserEntityService;
import com.trevis.startup.example.repositories.UserRepository;

@Service
public class UserServiceDefault implements UserEntityService {
    
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordService passwordService;
    @Autowired
    DepartmentEntityService departmentEntityService;


    @Override
    public UserEntity create(UserCreationDto payload, Long userId) {

        var query = userRepository.findById(userId);
        if(query.get().getRole() != UserEntityRole.ADMINISTRATOR) throw new ForbiddenException();

        var departament = departmentEntityService.get(payload.departmentId());
        String password = passwordService.applyCryptography("123456");

        UserEntity newUser = new UserEntity();
        newUser.setPassword(password);
        newUser.setRole(UserEntityRole.fromInteger(payload.role()));
        newUser.setUsername(payload.username());
        newUser.setDepartment(departament);

        return userRepository.save(newUser);
    }

    @Override
    public UserEntity get(String username) {

        var query = userRepository.findByUsername(username);
        if(query.isEmpty()) throw new NotFoundException();

        return query.get();
    }
    @Override
    public UserEntity get(Long id) {
        
        var query = userRepository.findById(id);
        if(query.isEmpty()) throw new NotFoundException();

        return query.get();
    }

    @Override
    public UserEntity update(Long id, UserUpdateDto payload, Long userId) {

        var query = userRepository.findById(id);
        if(query.isEmpty()) throw new NotFoundException();

        UserEntity user = query.get();

        var requestUser = userRepository.findById(userId);
        if(requestUser.isEmpty()) throw new UnauthorizedException();
        if(user.getId() != userId && requestUser.get().getRole() != UserEntityRole.ADMINISTRATOR) throw new ForbiddenException();

        if(payload.password() != null) {
            user.setPassword(passwordService.applyCryptography(payload.password()));
        }
        if(payload.role() != null) {
            user.setRole(UserEntityRole.fromInteger(payload.role()));
        } 
        if(payload.departmentId() != null) {
            var department = departmentEntityService.get(payload.departmentId());
            user.setDepartment(department);
        }

        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        
        var query = userRepository.findById(id);
        if(query.isEmpty()) throw new NotFoundException();

        userRepository.deleteById(id);
    }
}
