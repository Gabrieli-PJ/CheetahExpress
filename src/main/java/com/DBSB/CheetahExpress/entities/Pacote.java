package com.DBSB.CheetahExpress.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "pacotes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private User cliente;

    @Column(columnDefinition = "text")
    private String descricao;

    @NotNull
    @Positive
    @Column(name = "peso_kg", nullable = false)
    private Double pesoKg;

    @Column(name = "valor_declarado")
    private Double valorDeclarado;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrega_id")
    private Entrega entrega;
}
