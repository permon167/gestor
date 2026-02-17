package com.pablo.backend.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

//Entity: Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos
//Table: Especifica el nombre de la tabla en la base de datos a la que se mapeará esta entidad
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ManyToOne: Indica una relación de muchos a uno entre Pedido y Cliente, lo que significa que cada pedido está asociado a un cliente específico. 
    // El atributo fetch = FetchType.LAZY indica que la información del cliente se cargará de forma perezosa, es decir, solo cuando se acceda a ella. 
    // El atributo optional = false indica que esta relación es obligatoria, es decir, un pedido no puede existir sin estar asociado a un cliente.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //JoinColumn: Especifica la columna en la tabla de pedidos que se utilizará para almacenar la clave foránea que referencia al cliente.
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
