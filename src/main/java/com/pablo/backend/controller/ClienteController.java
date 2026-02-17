package com.pablo.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablo.backend.entity.Cliente;
import com.pablo.backend.service.ClienteService;

import jakarta.validation.Valid;

// El controlador ClienteController maneja las solicitudes relacionadas con los clientes
// La anotación @RestController indica que esta clase es un controlador REST y que los métodos devolverán datos directamente en el cuerpo de la respuesta
// La anotación @RequestMapping("/api/clientes") establece la ruta base para todas las solicitudes manejadas por este controlador
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // El método listar() maneja las solicitudes GET a la ruta "/api/clientes" y devuelve una lista de todos los clientes registrados en la base de datos.
    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listarTodos();
    }

// El método crear() maneja las solicitudes POST a la ruta "/api/clientes" y recibe un objeto Cliente en el cuerpo de la solicitud que representa los datos del cliente a crear.
// La anotación @RequestBody indica que el objeto Cliente debe ser deserializado a partir del cuerpo de la solicitud y @Valid asegura que el objeto sea validado antes de ser procesado.
    @PostMapping
    public Cliente crear(@Valid @RequestBody Cliente c) {
        return clienteService.crear(c);
    }
}
