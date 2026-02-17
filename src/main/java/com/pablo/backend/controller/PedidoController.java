package com.pablo.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablo.backend.dto.PedidoCreateRequest;
import com.pablo.backend.dto.PedidoResponse;
import com.pablo.backend.entity.Cliente;
import com.pablo.backend.entity.Pedido;
import com.pablo.backend.repository.ClienteRepository;
import com.pablo.backend.repository.PedidoRepository;

import jakarta.validation.Valid;

// El controlador PedidoController maneja las solicitudes relacionadas con los pedidos de un cliente específico
// La anotación @RestController indica que esta clase es un controlador REST y que los métodos devolverán datos directamente en el cuerpo de la respuesta
// La anotación @RequestMapping("/api/clientes/{clienteId}/pedidos") establece la ruta base para todas las solicitudes manejadas por este controlador,
//  donde {clienteId} es un parámetro de ruta que representa el ID del cliente al que pertenecen los pedidos
@RestController
@RequestMapping("/api/clientes/{clienteId}/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepo;
    private final ClienteRepository clienteRepo;

    // El constructor de PedidoController recibe instancias de PedidoRepository y ClienteRepository, que se asignan a las variables de instancia 'pedidoRepo' y 'clienteRepo' respectivamente para su uso en los métodos del controlador
    public PedidoController(PedidoRepository pedidoRepo, ClienteRepository clienteRepo) {
        this.pedidoRepo = pedidoRepo;
        this.clienteRepo = clienteRepo;
    }

    // El método listar() maneja las solicitudes GET a la ruta "/api/clientes/{clienteId}/pedidos" 
    // y devuelve una lista de pedidos asociados al cliente con el ID especificado.
    @GetMapping
    public List<PedidoResponse> listar(@PathVariable Long clienteId) {
        //stream() se utiliza para convertir la lista de pedidos obtenida del repositorio en un flujo de datos, 
        // luego map() se aplica a cada pedido para transformarlo en un objeto PedidoResponse que contiene solo la información relevante (ID del pedido, ID del cliente y total), y finalmente toList() convierte el flujo de datos resultante en una lista que se devuelve como respuesta.
        return pedidoRepo.findByClienteId(clienteId).stream().map(p -> new PedidoResponse(p.getId(), p.getCliente().getId(), p.getTotal())).toList();
    }

    // El método crear() maneja las solicitudes POST a la ruta "/api/clientes/{clienteId}/pedidos" y recibe un objeto PedidoCreateRequest en el cuerpo de la solicitud.
    // La anotación @Valid asegura que el objeto PedidoCreateRequest sea validado antes de ser procesado.
    // El método primero verifica si el cliente con el ID especificado existe en la base de datos utilizando el repositorio clienteRepo. Si el cliente no existe, se lanza una excepción IllegalArgumentException.
    // Si el cliente existe, se crea un nuevo objeto Pedido, se asigna el cliente y el total del pedido utilizando los datos del objeto PedidoCreateRequest, y luego se guarda el pedido en la base de datos utilizando el repositorio pedidoRepo.
    // Finalmente, se devuelve un objeto PedidoResponse que contiene el ID del pedido creado, el ID del cliente y el total del pedido como respuesta.
    @PostMapping
    public PedidoResponse crear(@PathVariable Long clienteId, @Valid @RequestBody PedidoCreateRequest req) {
        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no existe"));

        Pedido p = new Pedido();
        p.setCliente(cliente);
        p.setTotal(req.getTotal());

        Pedido saved = pedidoRepo.save(p);
        return new PedidoResponse(saved.getId(), clienteId, saved.getTotal());
    }
}
