package com.DBSB.CheetahExpress.services;


import com.DBSB.CheetahExpress.entities.User;
import com.DBSB.CheetahExpress.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public UsuarioDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByNome(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getNome())
                .password(user.getSenha())
                .roles(user.getRole().name())
                .build();
    }
}
