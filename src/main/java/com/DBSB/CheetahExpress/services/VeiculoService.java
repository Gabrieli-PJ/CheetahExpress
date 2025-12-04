package com.DBSB.CheetahExpress.services;


import com.DBSB.CheetahExpress.dtos.VeiculoCreateRequest;
import com.DBSB.CheetahExpress.dtos.VeiculoResponse;
import com.DBSB.CheetahExpress.dtos.VeiculoUpdateRequest;
import com.DBSB.CheetahExpress.entities.Veiculo;
import com.DBSB.CheetahExpress.mappers.VeiculoMapper;
import com.DBSB.CheetahExpress.repositories.VeiculoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository repository;

    @Transactional
    public VeiculoResponse create(VeiculoCreateRequest dto) {
        Veiculo veiculo = VeiculoMapper.toEntity(dto);
        repository.save(veiculo);
        return VeiculoMapper.toResponse(veiculo);
    }

    public VeiculoResponse findById(Long id) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        return VeiculoMapper.toResponse(veiculo);
    }

    public List<VeiculoResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(VeiculoMapper::toResponse)
                .toList();
    }

    @Transactional
    public VeiculoResponse update(Long id, VeiculoUpdateRequest dto) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        VeiculoMapper.updateEntity(veiculo, dto);
        repository.save(veiculo);
        return VeiculoMapper.toResponse(veiculo);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Veículo não encontrado");
        }
        repository.deleteById(id);
    }
}

