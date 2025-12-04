package com.DBSB.CheetahExpress.dtos;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RotaUpdateRequest {

    public String origem;

    public String destino;

    @PositiveOrZero
    public Double distanciaKm;

    Integer tempoEstimado;
}


