package com.trevis.startup.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trevis.startup.example.model.Service;

@Repository
public interface ServiceJPARepository extends JpaRepository<Service, Long> {
    
}
