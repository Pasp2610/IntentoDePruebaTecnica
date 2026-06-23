package com.prueba.demo.repository;

import com.prueba.demo.dto.PeliculaDto;
import com.prueba.demo.model.Genero;
import com.prueba.demo.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero,Long> {
    Pelicula encontrarPorNombre(String nombre);

}
