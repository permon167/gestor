package com.pablo.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pablo.backend.dto.PedidoCreateRequest;
import com.pablo.backend.dto.PedidoResponse;
import com.pablo.backend.entity.Cliente;
import com.pablo.backend.entity.Pedido;
import com.pablo.backend.repository.ClienteRepository;
import com.pablo.backend.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final ClienteRepository clienteRepo;

    public PedidoService(PedidoRepository pedidoRepo, ClienteRepository clienteRepo) {
        this.pedidoRepo = pedidoRepo;
        this.clienteRepo = clienteRepo;
    }

    // El método listarPorCliente() recibe un clienteId como parámetro, verifica si el cliente existe y luego consulta el repositorio de pedidos para obtener una lista de pedidos asociados a ese cliente. 
    // Los pedidos se transforman en objetos PedidoResponse antes de ser devueltos.
    public List<PedidoResponse> listarPorCliente(Long clienteId) {
        if (!clienteRepo.existsById(clienteId)) {
            throw new IllegalArgumentException("Cliente no existe");
        }
        return pedidoRepo.findByClienteId(clienteId)
                .stream()
                .map(p -> new PedidoResponse(p.getId(), p.getCliente().getId(), p.getTotal()))
                .toList();
    }

    // El método crear() recibe un clienteId y un objeto PedidoCreateRequest como parámetros. 
    // Primero verifica si el cliente existe, luego crea un nuevo objeto Pedido, lo asocia con el cliente y establece el total del pedido.
    public PedidoResponse crear(Long clienteId, PedidoCreateRequest req) {
        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no existe"));

        Pedido p = new Pedido();
        p.setCliente(cliente);
        p.setTotal(req.getTotal());

        Pedido saved = pedidoRepo.save(p);
        return new PedidoResponse(saved.getId(), clienteId, saved.getTotal());
    }
}
