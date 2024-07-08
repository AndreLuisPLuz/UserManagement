package com.javaProject.startup.project.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaProject.startup.project.dto.JWTUserData;
import com.javaProject.startup.project.dto.PostServiceData;
import com.javaProject.startup.project.dto.ServiceDto;
import com.javaProject.startup.project.model.Service;
import com.javaProject.startup.project.model.UserData;
import com.javaProject.startup.project.repositories.ServiceRepository;
import com.javaProject.startup.project.repositories.UserRepository;
import com.javaProject.startup.project.services.JWTService;
import com.javaProject.startup.project.services.ServiceService;

@RestController
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @Autowired
    JWTService<JWTUserData> jwtService;

    @Autowired
    UserRepository userRepo;
    
    @Autowired
    ServiceRepository serviceRepo;

    //..Tem que mudar a forma de criptografia das senhas, pois não está funcionando quando dá update na senha
    //De modo que não conseguimos fazer os testes das rotas do serviço, o get service conseguimos uma lista vazia
    //por não ter serviço cadastrado, e o resto não conseguimos testar

    @GetMapping("/service")
    public ResponseEntity<List<ServiceDto>> getService(
            @RequestParam String query,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestHeader("authorization") String jwt) {
        Map<String, Object> auth = jwtService.validate(jwt);
        if(auth == null) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
        }

        var matchingServices = serviceService.get(query, page, size);
        var result = matchingServices.stream()
            .map(s -> ServiceDto.buildFromEntity(s))
            .collect(Collectors.toList());
        
        return ResponseEntity.ok().body(result);
    }
    
    @PostMapping("/service") 
    public ResponseEntity<String> registerService(
            @RequestBody PostServiceData obj,
            @RequestHeader("authorization") String jwt) {
        Map<String, Object> auth = jwtService.validate(jwt);

        if(auth == null) {
            return new ResponseEntity<>(
                "Failed authentication :/", 
                HttpStatus.UNAUTHORIZED
            );
        }

        Integer role = (Integer) auth.get("role");
        if(role.intValue() != 1){
            return new ResponseEntity<>(
                "User does not have permission to execute such task :/", 
                HttpStatus.FORBIDDEN
            );
        }

        Long id = Long.parseLong(auth.get("id").toString());
        var user = userRepo.findById(id);

        if(!user.isPresent()) {
            return new ResponseEntity<>(
                "Session expired :/", 
                HttpStatus.BAD_REQUEST
            );
        }

        Service newService = new Service();
        newService.setName(obj.name());
        newService.setDescription(obj.description());
        newService.setIntern(obj.intern());
        newService.setManager((UserData) user.get());

        serviceRepo.save(newService);

        return new ResponseEntity<>(
            "Service registered successfully!", 
            HttpStatus.OK
        );
    }

    @PutMapping("/service/{id}")
    public ResponseEntity<String> updateService(@RequestBody PostServiceData obj, @PathVariable Long id, @RequestHeader("authorization") String jwt) {
        Map<String, Object> auth = jwtService.validate(jwt);

        if(auth == null) {
            return new ResponseEntity<>(
                "Failed authentication :/", 
                HttpStatus.UNAUTHORIZED
            );
        }

        var service = serviceRepo.findById(id);

        if(!service.isPresent()) {
            return new ResponseEntity<>(
                "Session expired :/", 
                HttpStatus.BAD_REQUEST
            );
        }

        if(!(service.get().getManager() == auth.get("id"))) {
            return new ResponseEntity<>(
                "User does not have permission to execute such task :/", 
                HttpStatus.FORBIDDEN
            );
        }

        service.get().setName(obj.name());
        service.get().setDescription(obj.description());
        service.get().setIntern(obj.intern());

        serviceRepo.save(service.get());

        return new ResponseEntity<>(
            "Service registered successfully!", 
            HttpStatus.OK
        );
    }

    @DeleteMapping("/service/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Long id, @RequestHeader("authorization") String jwt) {
        Map<String, Object> auth = jwtService.validate(jwt);

        if(auth == null) {
            return new ResponseEntity<>(
                "Failed authentication :/", 
                HttpStatus.UNAUTHORIZED
            );
        }

        var service = serviceRepo.findById(id);

        if(!service.isPresent()) {
            return new ResponseEntity<>(
                "Session expired :/", 
                HttpStatus.BAD_REQUEST
            );
        }

        if(!(service.get().getManager() == auth.get("id"))) {
            return new ResponseEntity<>(
                "User does not have permission to execute such task :/", 
                HttpStatus.FORBIDDEN
            );
        }

        serviceRepo.delete(service.get());

        return new ResponseEntity<>(
                "Service deleted successfully", 
                HttpStatus.OK
            );
    }
}