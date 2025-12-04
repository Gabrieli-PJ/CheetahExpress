package com.DBSB.CheetahExpress.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "rotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 200)
    private String origem;

    @NotBlank
    @Column(nullable = false, length = 200)
    private String destino;

    @Column(name = "distancia_km")
    private Double distanciaKm;

    private Integer tempoEstimado;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "rota")
    private java.util.List<Entrega> entregas = new java.util.ArrayList<>();
}

