package com.javaProject.startup.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaProject.startup.project.model.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {

    public List<UserData> findByUsername(String username);
}
