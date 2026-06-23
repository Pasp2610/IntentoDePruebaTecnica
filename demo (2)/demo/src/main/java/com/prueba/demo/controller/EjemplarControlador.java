package com.prueba.demo.controller;

import com.prueba.demo.model.Ejemplares;
import com.prueba.demo.model.Pelicula;
import com.prueba.demo.service.EjemplarService;
import com.prueba.demo.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ejemplar")
public class EjemplarControlador {
    @Autowired
    private EjemplarService ejemplarService;
    @Autowired
    private PeliculaService peliculaService;

    @PostMapping
    public ResponseEntity<Ejemplares>añadirEjemplar(@RequestBody Ejemplares ejemplares){
        Ejemplares añadir= ejemplarService.agregarEjemplar(ejemplares);
        return new ResponseEntity<>(añadir, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Ejemplares>>listarEjemplares(){
       List <Ejemplares> listar= ejemplarService.listarEjemplares();
       return ResponseEntity.ok(listar);
    }
    @GetMapping("/disponibles/{peliculaId}")
    public ResponseEntity<List<Ejemplares>>validarDisponibilidad(@PathVariable Long peliculaId){
        Pelicula pelicula= peliculaService.buscarPorId(peliculaId);
        List<Ejemplares>disponibles= ejemplarService.ejemplaresDisponibles(pelicula);
        return ResponseEntity.ok(disponibles);

    }


}
