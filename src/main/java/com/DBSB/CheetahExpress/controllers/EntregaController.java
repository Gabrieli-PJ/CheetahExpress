package com.DBSB.CheetahExpress.controllers;


import com.DBSB.CheetahExpress.dtos.EntregaCreateRequest;
import com.DBSB.CheetahExpress.dtos.EntregaResponse;
import com.DBSB.CheetahExpress.entities.StatusEntrega;
import com.DBSB.CheetahExpress.services.EntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entregas")
@RequiredArgsConstructor
public class EntregaController {

    private final EntregaService entregaService;

    @PreAuthorize("hasAnyRole('ADMIN', 'MOTORISTA')")
    @PostMapping
    public ResponseEntity<EntregaResponse> create(@RequestBody EntregaCreateRequest dto) {
        EntregaResponse response = entregaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaResponse> findById(@PathVariable Long id) {
        EntregaResponse response = entregaService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MOTORISTA')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(
            @PathVariable Long id,
            @RequestParam("status") StatusEntrega status) {

        entregaService.atualizarStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entregaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}