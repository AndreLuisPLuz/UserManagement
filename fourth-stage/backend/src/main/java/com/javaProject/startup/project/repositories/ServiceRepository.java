package com.javaProject.startup.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaProject.startup.project.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {}
