package com.DBSB.CheetahExpress.mappers;


import com.DBSB.CheetahExpress.dtos.PacoteCreateRequest;
import com.DBSB.CheetahExpress.dtos.PacoteResponse;
import com.DBSB.CheetahExpress.entities.Pacote;
import org.springframework.stereotype.Component;

@Component
public class PacoteMapper {

    public static Pacote toEntity(PacoteCreateRequest dto) {
        Pacote p = new Pacote();
        p.setDescricao(dto.descricao);
        p.setPesoKg(dto.pesoKg);
        return p;
    }

    public static PacoteResponse toResponse(Pacote p) {
        return new PacoteResponse(
                p.getId(),
                p.getDescricao(),
                p.getPesoKg()
        );
    }
}

