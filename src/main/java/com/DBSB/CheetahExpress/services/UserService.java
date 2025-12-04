package com.DBSB.CheetahExpress.services;


import com.DBSB.CheetahExpress.entities.Role;
import com.DBSB.CheetahExpress.entities.User;
import com.DBSB.CheetahExpress.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registrarUser(String nome, String senha) {

        String senhaCriptografada = passwordEncoder.encode(senha);

        User user = User.builder()
                .nome(nome)
                .senha(senhaCriptografada)
                .role(Role.CLIENTE)
                .ativo(true)
                .build();

        return repository.save(user);
    }

    public Optional<User> findUserByNome(String nome) {
        return repository.findByNome(nome);
    }

    public boolean validarSenha(String senhaPura, String senhaCriptografada) {
        return passwordEncoder.matches(senhaPura, senhaCriptografada);
    }

    public User updateRole(Long userId, Role newRole) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setRole(newRole);

        return repository.save(user);
    }

    public void delete(Long userId) {
        if (!repository.existsById(userId)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        repository.deleteById(userId);
    }

}
