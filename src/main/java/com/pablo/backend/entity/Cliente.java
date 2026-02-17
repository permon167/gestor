package com.pablo.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//Entity: Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos
//Table: Especifica el nombre de la tabla en la base de datos a la que se mapeará esta entidad
@Entity
@Table(name = "clientes")
public class Cliente {

    //Id: Indica que el campo id es la clave primaria de la entidad
    //GeneratedValue: Especifica la estrategia de generación de valores para la clave primaria, en este caso, se utilizará la estrategia de identidad (IDENTITY) que es común para bases de datos como MySQL y PostgreSQL
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //NotBlank: Indica que el campo nombre no puede estar vacío o contener solo espacios en blanco
    //Column: Especifica las propiedades de la columna en la base de datos, en este caso, se indica que no puede ser nula (nullable = false) y que su longitud máxima es de 120 caracteres (length = 120)
    @NotBlank
    @Column(nullable = false, length = 120)
    private String nombre;

    //Email: Indica que el campo email debe contener una dirección de correo electrónico válida
    @Email
    @NotBlank
    @Column(nullable = false, unique = true, length = 120)
    private String email;

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
