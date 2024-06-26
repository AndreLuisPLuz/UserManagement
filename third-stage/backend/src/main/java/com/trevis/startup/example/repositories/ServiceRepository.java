package com.trevis.startup.example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.trevis.startup.example.entities.ServiceEntity;

@Repository
public interface ServiceRepository extends PagingAndSortingRepository<ServiceEntity, Long> {
    List<ServiceEntity> findByName(String name, Pageable pageable);
    Optional<ServiceEntity> findById(Long id);
    ServiceEntity save(ServiceEntity serviceModel);
    void deleteById(Long id); 
}
