package com.dev.resolveja.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.dev.resolveja.domain.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
@Table(name = "users", schema = "sc_resolveja")
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_users")
    @SequenceGenerator( 
        name = "seq_users",
        sequenceName = "sc_resolveja.seq_users",
        allocationSize = 1
    )
    private Long id;

    private String name;
    private String email;
    private String password;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "provider") 
    private List<ProviderService> providerServices;

    @OneToMany(mappedBy = "customer")
    private List<ServiceSolicitation> serviceSolicitations;

    @CreationTimestamp 
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp 
    @Column(name = "updated_at") 
    private LocalDateTime updatedAt;

}
