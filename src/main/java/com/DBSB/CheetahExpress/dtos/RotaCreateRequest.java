package com.DBSB.CheetahExpress.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RotaCreateRequest {
    @NotBlank
    public String origem;

    @NotBlank
    public String destino;

    @PositiveOrZero
    public Double distanciaKm;

    Integer tempoEstimado;
}

