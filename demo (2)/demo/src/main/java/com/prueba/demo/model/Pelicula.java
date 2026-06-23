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
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPelicula;

    private String  nombre;
    private  String descripción;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;



}
