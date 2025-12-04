package com.DBSB.CheetahExpress.services;

import com.DBSB.CheetahExpress.dtos.RotaCreateRequest;
import com.DBSB.CheetahExpress.dtos.RotaResponse;
import com.DBSB.CheetahExpress.dtos.RotaUpdateRequest;
import com.DBSB.CheetahExpress.entities.Rota;
import com.DBSB.CheetahExpress.mappers.RotaMapper;
import com.DBSB.CheetahExpress.repositories.RotaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RotaService {

    private final RotaRepository repository;

    @Transactional
    public RotaResponse create(RotaCreateRequest dto) {
        Rota rota = RotaMapper.toEntity(dto);
        repository.save(rota);
        return RotaMapper.toResponse(rota);
    }

    public RotaResponse findById(Long id) {
        Rota rota = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rota não encontrada"));
        return RotaMapper.toResponse(rota);
    }

    public List<RotaResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(RotaMapper::toResponse)
                .toList();
    }

    @Transactional
    public RotaResponse update(Long id, RotaUpdateRequest dto) {
        Rota rota = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rota não encontrada"));

        RotaMapper.updateEntity(rota, dto);
        repository.save(rota);
        return RotaMapper.toResponse(rota);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Rota não encontrada");
        }
        repository.deleteById(id);
    }
}

