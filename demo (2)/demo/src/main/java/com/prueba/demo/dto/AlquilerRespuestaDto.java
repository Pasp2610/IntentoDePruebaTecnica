package com.prueba.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlquilerRespuestaDto {
    private Long idAlquiler;
    private String nombrePelicula;
    private LocalDateTime fechaAlquiler;
    private LocalDateTime fechaDevolucion;
    private Double valorPagado;

}
