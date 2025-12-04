package com.DBSB.CheetahExpress.dtos;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeiculoUpdateRequest {

    Long motoristaId;

    @Size(min = 3, max = 20)
    public String placa;

    @Size(min = 2, max = 80)
    public String modelo;

    @PositiveOrZero
    public Double capacidadeKg;

    Integer ano;

    Boolean ativo;
}

