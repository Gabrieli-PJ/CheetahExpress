package com.DBSB.CheetahExpress.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacoteCreateRequest {
    @NotNull
    Long clienteId;

    public String descricao;

    @NotNull
    @Positive
    public Double pesoKg;

    Double valorDeclarado;
}

