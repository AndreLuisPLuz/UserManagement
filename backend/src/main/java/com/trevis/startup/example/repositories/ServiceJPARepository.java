package com.trevis.startup.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trevis.startup.example.model.Service;

public interface ServiceJPARepository extends JpaRepository<Service, Long> { }
