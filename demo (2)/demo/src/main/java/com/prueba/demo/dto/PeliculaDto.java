package com.prueba.demo.dto;

import com.prueba.demo.model.Genero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PeliculaDto {
    private String nombre;
    private String descripcion;
    private Genero genero;

}
