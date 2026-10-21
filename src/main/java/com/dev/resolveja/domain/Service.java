package com.dev.resolveja.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Entity 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor
@Table(name = "services", schema = "sc_resolveja")
public class Service {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_services")
    @SequenceGenerator(
        name = "seq_services",
        sequenceName = "sc_resolveja.seq_services",
        allocationSize = 1
    )
    private Long id;
    
    private String name;
    private String description;
    private Boolean active;

    @OneToMany(mappedBy = "service")
    private List<ProviderService> providerServices;

    @Column(name = "icon_url")
    private String iconUrl;

    @CreationTimestamp
    @Column(name = "created_at") 
    private LocalDateTime createdAt;

    @UpdateTimestamp 
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
