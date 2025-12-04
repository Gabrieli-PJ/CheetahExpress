package com.DBSB.CheetahExpress.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "veiculos", uniqueConstraints = {
        @UniqueConstraint(columnNames = "placa")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // motorista opcional (veículo pode existir sem motorista atribuído)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorista_id")
    private User motorista;

    @NotBlank
    @Column(nullable = false, length = 20, unique = true)
    private String placa;

    @NotBlank
    @Column(nullable = false, length = 80)
    private String modelo;

    @PositiveOrZero
    @Column(name = "capacidade_kg")
    private Double capacidadeKg;

    private Integer ano;

    @Column(nullable = false)
    @ColumnDefault("true")
    private Boolean ativo = true;

    // Entregas que estão usando esse veículo (bi-direcional, se necessário)
    @OneToMany(mappedBy = "veiculo")
    @Builder.Default
    private java.util.List<Entrega> entregas = new java.util.ArrayList<>();
}
