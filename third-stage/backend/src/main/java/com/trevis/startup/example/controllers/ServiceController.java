package com.trevis.startup.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.example.dto.requests.ServiceCreationDto;
import com.trevis.startup.example.dto.response.ServiceResponseDto;
import com.trevis.startup.example.entities.ServiceEntity;
import com.trevis.startup.example.interfaces.AuthService;
import com.trevis.startup.example.interfaces.ServiceEntityService;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    ServiceEntityService serviceService;
    @Autowired
    AuthService authService;

    
    @GetMapping("")
    public ResponseEntity<List<ServiceResponseDto>> get(
        @RequestHeader("Authorization") String token,
        @RequestParam String query,
        @RequestParam Integer page,
        @RequestParam Integer size
    ) {

        Long requestUserId = authService.extractUserId(token);

        var services = serviceService.get(query, page, size, requestUserId);
        List<ServiceResponseDto> responseList = services
            .stream()
            .map(x -> new ServiceResponseDto(x))
            .toList();

        return ResponseEntity.ok(responseList);
    }

    @PostMapping("")
    public ResponseEntity<?> post(@RequestHeader("Authorization") String token, @RequestBody ServiceCreationDto body) {
        
        Long requestUserId = authService.extractUserId(token);
        var service = serviceService.create(body, requestUserId);

        return ResponseEntity.status(201).body(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> patch(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody ServiceCreationDto body) {

        Long requestUserId = authService.extractUserId(token);      
        ServiceEntity updatedService = serviceService.update(id, body, requestUserId);

        return ResponseEntity.status(200).body(new ServiceResponseDto(updatedService));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        
        Long requestUserId = authService.extractUserId(token);
        serviceService.deleteById(id, requestUserId);

        return ResponseEntity.status(204).body(null);
    }
}
