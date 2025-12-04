package com.DBSB.CheetahExpress.services;

import com.DBSB.CheetahExpress.dtos.PacoteCreateRequest;
import com.DBSB.CheetahExpress.dtos.PacoteResponse;
import com.DBSB.CheetahExpress.dtos.RotaResponse;
import com.DBSB.CheetahExpress.entities.Pacote;
import com.DBSB.CheetahExpress.entities.User;
import com.DBSB.CheetahExpress.mappers.PacoteMapper;
import com.DBSB.CheetahExpress.mappers.RotaMapper;
import com.DBSB.CheetahExpress.repositories.PacoteRepository;
import com.DBSB.CheetahExpress.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacoteService {

    private final PacoteRepository repository;

    private final UserRepository userRepository;

    @Transactional
    public PacoteResponse create(PacoteCreateRequest dto) {

        User cliente = userRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Pacote pacote = PacoteMapper.toEntity(dto);
        pacote.setCliente(cliente);
        pacote.setValorDeclarado(dto.getValorDeclarado());

        repository.save(pacote);

        return PacoteMapper.toResponse(pacote);
    }


    public PacoteResponse findById(Long id) {
        Pacote pacote = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pacote não encontrado"));
        return PacoteMapper.toResponse(pacote);
    }

    public List<PacoteResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(PacoteMapper::toResponse)
                .toList();
    }


    public java.util.List<Pacote> findAllById(java.util.List<Long> ids) {

        var pacotes = repository.findAllById(ids);

        if (pacotes.size() != ids.size()) {
            throw new RuntimeException("Um ou mais pacotes não foram encontrados.");
        }

        return pacotes;
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Pacote não encontrado");
        }
        repository.deleteById(id);
    }
}

