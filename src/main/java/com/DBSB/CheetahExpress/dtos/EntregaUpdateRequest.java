package com.DBSB.CheetahExpress.dtos;


import com.DBSB.CheetahExpress.entities.StatusEntrega;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntregaUpdateRequest {

    public Long motoristaId;

    public Long veiculoId;

    public Long rotaId;

    public StatusEntrega status;

    LocalDate dataPrevisao;

    LocalDate dataEntrega;
}

