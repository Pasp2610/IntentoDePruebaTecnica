package com.prueba.demo.dto;

import com.prueba.demo.model.Genero;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeliculaRespuestaDto {

    private Long idPelicula;
    private String nombre;
    private Genero genero;
    private String descripcion;

}
