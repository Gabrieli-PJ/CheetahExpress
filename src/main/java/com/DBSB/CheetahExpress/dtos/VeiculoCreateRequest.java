package com.DBSB.CheetahExpress.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeiculoCreateRequest {
    // motoristaId opcional à criação
    Long motoristaId;

    @NotBlank
    public String placa;

    @NotBlank
    public String modelo;

    @PositiveOrZero
    public Double capacidadeKg;

    Integer ano;
}

