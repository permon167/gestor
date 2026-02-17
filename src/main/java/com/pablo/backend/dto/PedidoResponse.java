package com.pablo.backend.dto;

import java.math.BigDecimal;

public class PedidoResponse {

    private Long id;
    private Long clienteId;
    private BigDecimal total;

    public PedidoResponse(Long id, Long clienteId, BigDecimal total) {
        this.id = id;
        this.clienteId = clienteId;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public BigDecimal getTotal() {
        return total;
    }

}
