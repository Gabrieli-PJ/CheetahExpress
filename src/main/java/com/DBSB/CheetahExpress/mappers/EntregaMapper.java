package com.DBSB.CheetahExpress.mappers;


import com.DBSB.CheetahExpress.dtos.EntregaCreateRequest;
import com.DBSB.CheetahExpress.dtos.EntregaResponse;
import com.DBSB.CheetahExpress.dtos.EntregaUpdateRequest;
import com.DBSB.CheetahExpress.entities.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntregaMapper {

    // cria Entrega a partir do DTO e listas de entidades relacionadas
    public static Entrega toEntity(EntregaCreateRequest dto,
                                   User motorista,
                                   Veiculo veiculo,
                                   Rota rota,
                                   List<Pacote> pacotes) {

        Entrega e = new Entrega();
        e.setMotorista(motorista);
        e.setVeiculo(veiculo);
        e.setRota(rota);

        // status do DTO ou padrão
        e.setStatus(dto.getStatus() != null ? dto.getStatus() : StatusEntrega.AGUARDANDO);
        e.setDataPrevisao(dto.getDataPrevisao());

        // atribui pacotes à entrega (bidirecional)
        if (pacotes != null) {
            pacotes.forEach(p -> e.addPacote(p));
        }

        return e;
    }

    // atualiza entidade existente
    public static void updateEntity(Entrega e,
                                    EntregaUpdateRequest dto,
                                    User motorista,
                                    Veiculo veiculo,
                                    Rota rota) {

        if (dto.getMotoristaId() != null && motorista != null) e.setMotorista(motorista);
        if (dto.getVeiculoId() != null && veiculo != null) e.setVeiculo(veiculo);
        if (dto.getRotaId() != null && rota != null) e.setRota(rota);
        if (dto.getStatus() != null) e.setStatus(dto.getStatus());
        if (dto.getDataPrevisao() != null) e.setDataPrevisao(dto.getDataPrevisao());
        if (dto.getDataEntrega() != null) e.setDataEntrega(dto.getDataEntrega());
    }

    // converte Entrega -> Response (protege acessos LAZY com null checks)
    public static EntregaResponse toResponse(Entrega e) {
        Long motoristaId = e.getMotorista() != null ? e.getMotorista().getId() : null;
        Long veiculoId = e.getVeiculo() != null ? e.getVeiculo().getId() : null;
        List<Long> pacoteIds = e.getPacotes() != null
                ? e.getPacotes().stream().map(Pacote::getId).collect(Collectors.toList())
                : null;

        return new EntregaResponse(
                e.getId(),
                e.getStatus(),
                e.getCreatedAt(),
                e.getDataPrevisao(),
                motoristaId,
                veiculoId,
                pacoteIds
        );
    }
}
