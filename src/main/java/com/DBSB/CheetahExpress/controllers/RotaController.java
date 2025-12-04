package com.DBSB.CheetahExpress.controllers;


import com.DBSB.CheetahExpress.dtos.RotaCreateRequest;
import com.DBSB.CheetahExpress.dtos.RotaResponse;
import com.DBSB.CheetahExpress.dtos.RotaUpdateRequest;
import com.DBSB.CheetahExpress.services.RotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/rotas")
@RequiredArgsConstructor
public class RotaController {

    private final RotaService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'MOTORISTA')")
    @PostMapping
    public ResponseEntity<RotaResponse> create(@RequestBody RotaCreateRequest dto) {
        RotaResponse created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/rotas/" + created.id()))
                .body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RotaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MOTORISTA')")
    @GetMapping
    public ResponseEntity<List<RotaResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MOTORISTA')")
    @PutMapping("/{id}")
    public ResponseEntity<RotaResponse> update(
            @PathVariable Long id,
            @RequestBody RotaUpdateRequest dto) {

        return ResponseEntity.ok(service.update(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
