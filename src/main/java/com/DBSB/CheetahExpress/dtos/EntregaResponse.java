package com.DBSB.CheetahExpress.dtos;


import com.DBSB.CheetahExpress.entities.StatusEntrega;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public record EntregaResponse(
        Long id,
        StatusEntrega status,
        OffsetDateTime dataCriacao,
        LocalDate dataPrevista,
        Long motoristaId,
        Long veiculoId,
        List<Long> pacotesIds
) {}

