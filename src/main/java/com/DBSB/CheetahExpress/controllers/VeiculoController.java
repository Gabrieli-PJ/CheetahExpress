package com.DBSB.CheetahExpress.controllers;


import com.DBSB.CheetahExpress.dtos.VeiculoCreateRequest;
import com.DBSB.CheetahExpress.dtos.VeiculoResponse;
import com.DBSB.CheetahExpress.dtos.VeiculoUpdateRequest;
import com.DBSB.CheetahExpress.services.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'MOTORISTA')")
    @PostMapping
    public ResponseEntity<VeiculoResponse> create(@RequestBody VeiculoCreateRequest dto) {
        VeiculoResponse created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/veiculos/" + created.id()))
                .body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MOTORISTA')")
    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MOTORISTA')")
    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponse> update(
            @PathVariable Long id,
            @RequestBody VeiculoUpdateRequest dto) {

        return ResponseEntity.ok(service.update(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
