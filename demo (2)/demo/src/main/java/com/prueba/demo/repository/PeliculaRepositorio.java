package com.prueba.demo.repository;

import com.prueba.demo.dto.PeliculaDto;
import com.prueba.demo.model.Clientes;
import com.prueba.demo.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, Long> {
        List<Pelicula> findByNombreContaining(String nombre); // ✅ convención Spring
}
