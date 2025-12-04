package com.DBSB.CheetahExpress.mappers;


import com.DBSB.CheetahExpress.dtos.RotaCreateRequest;
import com.DBSB.CheetahExpress.dtos.RotaResponse;
import com.DBSB.CheetahExpress.dtos.RotaUpdateRequest;
import com.DBSB.CheetahExpress.entities.Rota;
import org.springframework.stereotype.Component;

@Component
public class RotaMapper {

    public static Rota toEntity(RotaCreateRequest dto) {
        Rota r = new Rota();
        r.setOrigem(dto.origem);
        r.setDestino(dto.destino);
        r.setDistanciaKm(dto.distanciaKm);
        return r;
    }

    public static void updateEntity(Rota r, RotaUpdateRequest dto) {
        if (dto.origem != null) r.setOrigem(dto.origem);
        if (dto.destino != null) r.setDestino(dto.destino);
        if (dto.distanciaKm != null) r.setDistanciaKm(dto.distanciaKm);
    }

    public static RotaResponse toResponse(Rota r) {
        return new RotaResponse(
                r.getId(),
                r.getOrigem(),
                r.getDestino()
        );
    }
}

