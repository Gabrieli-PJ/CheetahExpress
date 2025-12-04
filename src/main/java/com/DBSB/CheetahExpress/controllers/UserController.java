package com.DBSB.CheetahExpress.controllers;


import com.DBSB.CheetahExpress.entities.Role;
import com.DBSB.CheetahExpress.entities.User;
import com.DBSB.CheetahExpress.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<User> create(@RequestBody Map<String,String> dto) {
        User user = userService.registrarUser(dto.get("nome"), dto.get("senha"));
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/role")
    public ResponseEntity<User> updateRole(@PathVariable Long id, @RequestParam Role role) {
        User updated = userService.updateRole(id, role);
        return ResponseEntity.ok(updated);
    }
}

