package com.DBSB.CheetahExpress.mappers;


import com.DBSB.CheetahExpress.dtos.VeiculoCreateRequest;
import com.DBSB.CheetahExpress.dtos.VeiculoResponse;
import com.DBSB.CheetahExpress.dtos.VeiculoUpdateRequest;
import com.DBSB.CheetahExpress.entities.Veiculo;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper {

    public static Veiculo toEntity(VeiculoCreateRequest dto) {
        return Veiculo.builder()
                .placa(dto.placa)
                .modelo(dto.modelo)
                .capacidadeKg(dto.capacidadeKg)
                .build();
    }

    public static void updateEntity(Veiculo v, VeiculoUpdateRequest dto) {
        if (dto.placa != null) v.setPlaca(dto.placa);
        if (dto.modelo != null) v.setModelo(dto.modelo);
        if (dto.capacidadeKg != null) v.setCapacidadeKg(dto.capacidadeKg);
    }

    public static VeiculoResponse toResponse(Veiculo v) {
        return new VeiculoResponse(
                v.getId(),
                v.getModelo(),
                v.getPlaca()
        );
    }
}

