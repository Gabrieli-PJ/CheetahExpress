package com.DBSB.CheetahExpress.services;


import com.DBSB.CheetahExpress.dtos.EntregaCreateRequest;
import com.DBSB.CheetahExpress.dtos.EntregaResponse;
import com.DBSB.CheetahExpress.entities.*;
import com.DBSB.CheetahExpress.mappers.EntregaMapper;
import com.DBSB.CheetahExpress.repositories.EntregaRepository;
import com.DBSB.CheetahExpress.repositories.RotaRepository;
import com.DBSB.CheetahExpress.repositories.UserRepository;
import com.DBSB.CheetahExpress.repositories.VeiculoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntregaService {

    private final EntregaRepository repository;
    private final PacoteService pacoteService;
    private final UserRepository userRepository;
    private final VeiculoRepository veiculoRepository;
    private final RotaRepository rotaRepository;

    @Transactional
    public EntregaResponse create(EntregaCreateRequest dto) {

        List<Pacote> pacotes = pacoteService.findAllById(dto.pacotesIds);

        User motorista = userRepository.findById(dto.motoristaId)
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado"));

        Veiculo veiculo = dto.veiculoId == null ? null :
                veiculoRepository.findById(dto.veiculoId)
                        .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        Rota rota = dto.rotaId == null ? null :
                rotaRepository.findById(dto.rotaId)
                        .orElseThrow(() -> new RuntimeException("Rota não encontrada"));

        Entrega entrega = EntregaMapper.toEntity(dto, motorista, veiculo, rota, pacotes);
        repository.save(entrega);

        return EntregaMapper.toResponse(entrega);
    }

    public EntregaResponse findById(Long id) {
        Entrega entrega = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega não encontrada"));
        return EntregaMapper.toResponse(entrega);
    }

    @Transactional
    public void atualizarStatus(Long entregaId, StatusEntrega status) {
        Entrega entrega = repository.findById(entregaId)
                .orElseThrow(() -> new RuntimeException("Entrega não encontrada"));

        entrega.setStatus(status);
        repository.save(entrega);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new RuntimeException("Entrega não encontrada");
        repository.deleteById(id);
    }
}

