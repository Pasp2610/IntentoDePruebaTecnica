package com.prueba.demo.repository;

import com.prueba.demo.model.Ejemplares;
import com.prueba.demo.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EjemplarRepository extends JpaRepository<Ejemplares,Long> {

    List<Ejemplares> findByPeliculaIdPeliculaAndDisponibleTrue(Pelicula pelicula);
}
