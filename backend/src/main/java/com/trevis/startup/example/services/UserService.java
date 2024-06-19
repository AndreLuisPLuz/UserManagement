package com.trevis.startup.example.services;

import com.trevis.startup.example.dto.UserDTO;
import com.trevis.startup.example.model.User;
import com.trevis.startup.example.structure.ArrayMessageResult;
import com.trevis.startup.example.structure.DataResult;


public interface UserService {
    ArrayMessageResult Create(User user);
    ArrayMessageResult UpdatePassword(User user, String newPassword);
    DataResult<UserDTO> Get(String username);
}
