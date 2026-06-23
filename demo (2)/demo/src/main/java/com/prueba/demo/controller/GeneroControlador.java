package com.prueba.demo.controller;

import com.prueba.demo.model.Genero;
import com.prueba.demo.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/generos")
public class GeneroControlador {
    @Autowired
    private GeneroService generoService;


   @PostMapping
    public ResponseEntity<Genero>agregarGenero(@RequestBody Genero generoGuardar){
       Genero guardado= generoService.agregarGenero(generoGuardar);
       return new ResponseEntity<>(guardado, HttpStatus.CREATED);

   }
   @GetMapping
   public ResponseEntity<List<Genero>>listarGeneros(){
       List<Genero>obtener= generoService.listarGeneros();
       return ResponseEntity.ok(obtener);
   }
   @PutMapping("/id")
    public ResponseEntity<Genero>actualizar(@PathVariable  Genero genero,
                                            @RequestParam Double valor){
       Genero actualizar= generoService.actulizarValorAlquiler(genero,valor);
       return ResponseEntity.ok(actualizar);
   }

}
