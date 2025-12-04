package com.DBSB.CheetahExpress.dtos;


import com.DBSB.CheetahExpress.entities.StatusEntrega;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntregaCreateRequest {

    @NotNull
    public List<Long> pacotesIds;

    @NotNull
    public Long motoristaId;

    // veículo e rota opcionais no agendamento inicial
    public Long veiculoId;
    public Long rotaId;

    // status opcional; por padrão AGUARDANDO se ausente
    public StatusEntrega status;

    LocalDate dataPrevisao;
}

