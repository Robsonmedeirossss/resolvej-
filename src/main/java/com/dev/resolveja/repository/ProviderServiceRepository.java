package com.dev.resolveja.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.resolveja.domain.ProviderService;

public interface ProviderServiceRepository extends JpaRepository<ProviderService, Long> {

    Page<ProviderService> findAllByProviderId(Long providerId, Pageable pageable);
  
} 
