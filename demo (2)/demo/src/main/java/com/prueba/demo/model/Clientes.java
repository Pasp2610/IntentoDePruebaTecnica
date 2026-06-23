package com.prueba.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long clienteId;

    private Long cedula;
    private String nombres;
    private String apellidos;
    private String contraseña;
    private LocalDateTime fechaDeCreacion;
}
