package com.DBSB.CheetahExpress.repositories;


import com.DBSB.CheetahExpress.entities.Entrega;
import com.DBSB.CheetahExpress.entities.Pacote;
import com.DBSB.CheetahExpress.entities.StatusEntrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    List<Entrega> findByStatus(StatusEntrega status);

    List<Entrega> findByMotoristaId(Long motoristaId);

    List<Entrega> findByVeiculoId(Long veiculoId);

    List<Entrega> findByRotaId(Long rotaId);

    boolean existsByPacotes(List<Pacote> pacotes);
}

