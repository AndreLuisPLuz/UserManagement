package com.trevis.startup.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.example.dto.requests.UserCreationDto;
import com.trevis.startup.example.dto.requests.UserUpdateDto;
import com.trevis.startup.example.dto.response.UserReturnDto;
import com.trevis.startup.example.entities.UserEntity;
import com.trevis.startup.example.interfaces.AuthService;
import com.trevis.startup.example.interfaces.PasswordService;
import com.trevis.startup.example.interfaces.UserEntityService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserEntityService userService;
    @Autowired
    PasswordService passwordService;
    @Autowired
    AuthService authService;


    @PostMapping("")
    public ResponseEntity<UserReturnDto> create(@RequestHeader("Authorization") String token, @RequestBody UserCreationDto user) {

        Long userId = authService.extractUserId(token);
        var newUser = userService.create(user, userId);

        return ResponseEntity.status(201).body(new UserReturnDto(newUser));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody UserUpdateDto body) {

        Long userId = authService.extractUserId(token);
        UserEntity userUpdated = userService.update(id, body, userId);

        return ResponseEntity.ok().body(new UserReturnDto(userUpdated));
    }
}
