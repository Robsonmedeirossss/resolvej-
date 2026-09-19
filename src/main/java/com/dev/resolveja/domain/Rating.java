package com.dev.resolveja.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.dev.resolveja.domain.enums.RatingStatus;

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
import lombok.Data;

@Data 
@Entity 
@Table(name = "ratings", schema = "sc_resolveja")
public class Rating {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ratings")
    @SequenceGenerator(
        name = "seq_ratings",
        sequenceName = "sc_resolveja.seq_ratings",
        allocationSize = 1
    )
    private Long id;
    private Integer note;
    private String comment;

    @Enumerated(EnumType.STRING)
    private RatingStatus status;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_solicitation_id")
    private ServiceSolicitation serviceSolicitation;

    @CreationTimestamp 
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
