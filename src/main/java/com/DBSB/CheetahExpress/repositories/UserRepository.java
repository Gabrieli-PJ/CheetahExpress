package com.DBSB.CheetahExpress.repositories;

import com.DBSB.CheetahExpress.entities.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNome(@NotBlank String nome);

}

