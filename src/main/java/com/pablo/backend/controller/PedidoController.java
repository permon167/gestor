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
import com.pablo.backend.service.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes/{clienteId}/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // El método listar() maneja las solicitudes GET a la ruta "/api/clientes/{clienteId}/pedidos" y devuelve una lista de pedidos asociados al cliente especificado por el clienteId en la ruta.
    @GetMapping
    public List<PedidoResponse> listar(@PathVariable Long clienteId) {
        return pedidoService.listarPorCliente(clienteId);
    }

    // El método crear() maneja las solicitudes POST a la ruta "/api/clientes/{clienteId}/pedidos" y recibe un objeto PedidoCreateRequest en el cuerpo de la solicitud que representa los datos del pedido a crear para el cliente especificado por el clienteId en la ruta.
    @PostMapping
    public PedidoResponse crear(@PathVariable Long clienteId, @Valid @RequestBody PedidoCreateRequest req) {
        return pedidoService.crear(clienteId, req);
    }
}
