package com.dev.resolveja.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.resolveja.domain.ServiceSolicitation;

public interface ServiceSolicitationRepository extends JpaRepository<ServiceSolicitation, Long>{
   

    @Query(value = """
        SELECT * FROM
        services_solicitations ss
        where ss.customer_id = :customerId
    """,
    countQuery = """
        SELECT COUNT(*)
        FROM service_solicitations ss
        WHERE ss.customer_id = :customerId 
    """,
    nativeQuery = true)
    Page<ServiceSolicitation> findAllByCustomerId(@Param("customerId") Long customerId, Pageable pageable);

    @Query(value = """
        SELECT ss.* FROM
        services_solicitations ss
        inner join provider_services ps on ss.provider_service_id = ps.id
        where ps.provider_id = :providerId
    """,
    countQuery = """
           SELECT COUNT(*)
        FROM service_solicitations ss
        INNER JOIN provider_services ps
            ON ss.provider_service_id = ps.id
        WHERE ps.provider_id = :providerId  
        
    """,
    nativeQuery = true)
    Page<ServiceSolicitation> findAllByProviderId(@Param("providerId") Long providerId, Pageable pageable);
}
