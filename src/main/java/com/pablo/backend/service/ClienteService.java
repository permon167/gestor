package com.pablo.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pablo.backend.entity.Cliente;
import com.pablo.backend.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepo;

    public ClienteService(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    public List<Cliente> listarTodos() {
        return clienteRepo.findAll();
    }

    public Cliente crear(Cliente cliente) {
        if (clienteRepo.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Ya existe un cliente con el email: " + cliente.getEmail());
        }
        return clienteRepo.save(cliente);
    }
}
