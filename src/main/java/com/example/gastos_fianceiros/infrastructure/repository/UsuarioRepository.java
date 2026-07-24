package com.example.gastos_fianceiros.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gastos_fianceiros.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}