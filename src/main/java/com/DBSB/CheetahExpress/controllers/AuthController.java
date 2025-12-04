package com.DBSB.CheetahExpress.controllers;


import com.DBSB.CheetahExpress.entities.User;
import com.DBSB.CheetahExpress.security.JwtUtil;
import com.DBSB.CheetahExpress.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {

        User user = userService.registrarUser(
                request.get("nome"),
                request.get("senha")
        );

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        Optional<User> userOpt = userService.findUserByNome(request.get("nome"));

        if (userOpt.isEmpty())
            return ResponseEntity.badRequest().body("Usuário não encontrado");

        User user = userOpt.get();

        boolean senhaValida = userService.validarSenha(
                request.get("senha"),
                user.getSenha()
        );

        if (!senhaValida)
            return ResponseEntity.badRequest().body("Senha inválida");

        String token = JwtUtil.generateJwtToken(user.getNome());

        return ResponseEntity.ok(Map.of("token", token));
    }
}
