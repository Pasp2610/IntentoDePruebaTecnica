package com.prueba.demo.controller;

import com.prueba.demo.model.Pelicula;
import com.prueba.demo.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaControlador {
    @Autowired
    private PeliculaService peliculaService;
    @PostMapping
    public ResponseEntity<Pelicula>agregarPelicula(@RequestBody  Pelicula pelicula){
        Pelicula guardar = peliculaService.agregarPelicula(pelicula);
        return new ResponseEntity<>(guardar, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Pelicula>>ListarPelicula(){
       List< Pelicula> listar =peliculaService.listar();
       return ResponseEntity.ok(listar);
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<Pelicula>>buscarPorNombre(@RequestParam String nombre){
        List<Pelicula>buscarPorNombre= peliculaService.encontrarPeliculaPorNombre
                (nombre);
        return ResponseEntity.ok(buscarPorNombre);
    }
    @PutMapping ("/{id}")
    public ResponseEntity<Pelicula>actualizarPelicula
            (@PathVariable Long id,
             @RequestBody Pelicula valor){
        Pelicula actualizar = peliculaService.actualizarPelicula(id,valor);
        return ResponseEntity.ok(actualizar);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
         peliculaService.eliminarPorId(id);
         return ResponseEntity.noContent().build();
    }



}
