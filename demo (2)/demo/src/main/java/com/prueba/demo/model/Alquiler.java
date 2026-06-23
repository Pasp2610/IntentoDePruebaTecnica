package com.prueba.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long idAlquiler;
    private LocalDateTime fechaAlquiler;
    private LocalDateTime fechaDevolucion;
    private LocalDateTime devolucionCliente;
    private Double valorPagado;

    @ManyToOne
    @JoinColumn(name = "clientes_id")
    private Clientes cliente;

    @ManyToOne
    @JoinColumn(name = "ejemplar_id")
    private Ejemplares ejemplar;

}
