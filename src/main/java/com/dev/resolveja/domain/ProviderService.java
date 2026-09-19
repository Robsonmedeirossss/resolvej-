package com.dev.resolveja.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data 
@Entity()
@Table(name = "provider_services", schema = "sc_resolveja")
public class ProviderService {

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_provider_services")
    @SequenceGenerator(
        name = "seq_provider_services",
        sequenceName = "sc_resolveja.seq_provider_services",
        allocationSize = 1
    ) 
    private Long id;

    private String name;
    private String description;
    private Boolean active;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private User provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Service service;

    @CreationTimestamp 
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp 
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
