package com.dev.resolveja.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.dev.resolveja.domain.enums.SolicitationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data 
@Entity
@Builder 
@Table(name = "service_solicitations", schema = "sc_resolveja") 
public class ServiceSolicitation {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_service_solicitations")
    @SequenceGenerator(
        name = "seq_service_solicitations",
        sequenceName = "sc_resolveja.seq_service_solicitations",
        allocationSize = 1
    )
    private Long id;
    private BigDecimal price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private SolicitationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_service_id")
    private ProviderService providerService;

    @OneToOne(mappedBy = "serviceSolicitation")
    private Rating ratings;

    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
    
    @CreationTimestamp 
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @UpdateTimestamp 
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
