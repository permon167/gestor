package com.pablo.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablo.backend.entity.Cliente;
import com.pablo.backend.repository.ClienteRepository;

import jakarta.validation.Valid;

// El controlador ClienteController maneja las solicitudes relacionadas con los clientes
// La anotación @RestController indica que esta clase es un controlador REST y que los métodos devolverán datos directamente en el cuerpo de la respuesta
// La anotación @RequestMapping("/api/clientes") establece la ruta base para todas las solicitudes manejadas por este controlador
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    // El repositorio ClienteRepository se inyecta a través del constructor para acceder a los datos de los clientes
    private final ClienteRepository repo;

    // El constructor de ClienteController recibe una instancia de ClienteRepository, que se asigna a la variable de instancia 'repo' para su uso en los métodos del controlador
    public ClienteController(ClienteRepository repo) {
        this.repo = repo;
    }

    // El método listar() maneja las solicitudes GET a la ruta "/api/clientes" y devuelve una lista de todos los clientes almacenados en la base de datos utilizando el método findAll() del repositorio
    @GetMapping
    public List<Cliente> listar() {
        return repo.findAll();
    }

    // El método crear() maneja las solicitudes POST a la ruta "/api/clientes" y recibe un objeto Cliente en el cuerpo de la solicitud. 
    // La anotación @Valid asegura que el objeto Cliente sea validado antes de ser procesado. 
    // Si la validación es exitosa, el cliente se guarda en la base de datos utilizando el método save() del repositorio y se devuelve el cliente creado como respuesta.
    @PostMapping
    public Cliente crear(@Valid @RequestBody Cliente c) {
        return repo.save(c);
    }

}
