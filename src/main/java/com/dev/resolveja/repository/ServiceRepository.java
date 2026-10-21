package com.dev.resolveja.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.resolveja.domain.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
 
    Optional<Service> findByName(String name);
    
}
