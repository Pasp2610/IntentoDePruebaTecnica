package com.prueba.demo.controller;

import com.prueba.demo.model.Clientes;
import com.prueba.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clientes")
public class ClienteControlador {
    @Autowired
    private ClienteService clienteService;
    @PostMapping
    public ResponseEntity<Clientes>agregarCliente(@RequestBody Clientes clientes ){
        Clientes añadir= clienteService.añadirCliente(clientes);
        return new ResponseEntity<>(añadir, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Clientes>>mostrarClientes(){
        List<Clientes>mostrar=clienteService.mostrarClientes();
        return ResponseEntity.ok(mostrar);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Clientes>encontrarPorId(@PathVariable Long id){
        Clientes encontrarID= clienteService.encontrarClientePorId(id);
        return ResponseEntity.ok(encontrarID);
    }

    @PostMapping("/login")
    public ResponseEntity<Clientes> login(@RequestParam String cedula
            , @RequestParam String contraseña) {
        Clientes validarEntrada= clienteService.login(cedula,contraseña);
        return  new ResponseEntity<>(validarEntrada,HttpStatus.OK);
    }

}
