package com.prueba.demo.service;

import com.prueba.demo.exception.MovieNotFound;
import com.prueba.demo.model.Ejemplares;
import com.prueba.demo.model.Pelicula;
import com.prueba.demo.repository.EjemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjemplarService {
    @Autowired
    private EjemplarRepository ejemplarRepository;
    public Ejemplares agregarEjemplar(Ejemplares ejemplares){
        ejemplares.setDisponible(true);
        return ejemplarRepository.save(ejemplares);
    }
    public List<Ejemplares> listarEjemplares(){
        return ejemplarRepository.findAll();

    }
    public List<Ejemplares>ejemplaresDisponibles(Pelicula pelicula){
        List<Ejemplares>disponibles= ejemplarRepository.findByPeliculaIdPeliculaAndDisponibleTrue
                (pelicula);
        if (disponibles.isEmpty()){
            throw new MovieNotFound(
                    "no hay ejemplares disponibles"
                    +pelicula.getNombre()
            );

        }
        return disponibles;
    }
}
