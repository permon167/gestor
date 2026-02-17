package com.pablo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pablo.backend.entity.Cliente;

// La interfaz ClienteRepository extiende JpaRepository, lo que proporciona métodos CRUD y de paginación
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
// Método personalizado para verificar si un cliente con un correo electrónico específico ya existe

    boolean existsByEmail(String email);
}
