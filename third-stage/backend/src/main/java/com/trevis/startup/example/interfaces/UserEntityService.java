package com.trevis.startup.example.interfaces;

import com.trevis.startup.example.dto.requests.UserCreationDto;
import com.trevis.startup.example.dto.requests.UserUpdateDto;
import com.trevis.startup.example.entities.UserEntity;

public interface UserEntityService {
    UserEntity create(UserCreationDto payload, Long userId);
    UserEntity get(Long id);
    UserEntity get(String username);
    UserEntity update(Long id, UserUpdateDto payload, Long userId);
    void delete(Long id);
}