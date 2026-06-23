package com.prueba.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Ejemplares {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEjemplar;

    private String codigoUnico;
    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;
}
