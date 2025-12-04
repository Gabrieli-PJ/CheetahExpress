package com.DBSB.CheetahExpress.controllers;


import com.DBSB.CheetahExpress.dtos.PacoteCreateRequest;
import com.DBSB.CheetahExpress.dtos.PacoteResponse;
import com.DBSB.CheetahExpress.services.PacoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pacotes")
@RequiredArgsConstructor
public class PacoteController {

    private final PacoteService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'MOTORISTA')")
    @PostMapping
    public ResponseEntity<PacoteResponse> create(@RequestBody PacoteCreateRequest dto) {
        PacoteResponse created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/pacotes/" + created.id()))
                .body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacoteResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MOTORISTA')")
    @GetMapping
    public ResponseEntity<List<PacoteResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
