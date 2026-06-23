package com.prueba.demo.controller;

import com.prueba.demo.dto.ReporteAlquiler;
import com.prueba.demo.model.Alquiler;
import com.prueba.demo.model.Clientes;
import com.prueba.demo.model.Pelicula;
import com.prueba.demo.service.AlquilerService;
import com.prueba.demo.service.ClienteService;
import com.prueba.demo.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerControlador {

    @Autowired
    private AlquilerService alquilerService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PeliculaService peliculaService;

    @PostMapping("/alquilar")
    public ResponseEntity<Alquiler> alquilarPelicula(
            @RequestParam Long peliculaId,
            @RequestParam Long clienteId) {
        Pelicula pelicula = peliculaService.buscarPorId(peliculaId);
        Clientes cliente = clienteService.encontrarClientePorId(clienteId);
        Alquiler alquiler = alquilerService.alquilarPelicula(pelicula, cliente);
        return new ResponseEntity<>(alquiler, HttpStatus.CREATED);
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity<Alquiler> devolverPelicula(@PathVariable Long id) {
        Alquiler devolver = alquilerService.devolverPelicula(id);
        return ResponseEntity.ok(devolver);
    }

    @GetMapping("/historial/{clienteId}")
    public ResponseEntity<List<Alquiler>> historialCliente(
            @PathVariable Long clienteId) {
        Clientes cliente = clienteService.encontrarClientePorId(clienteId);
        List<Alquiler> historial = alquilerService.historialCliente(cliente);
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/reporte")
    public ResponseEntity<List<ReporteAlquiler>> generarReporte() {
        List<ReporteAlquiler> reporte = alquilerService.generarReporte();
        return ResponseEntity.ok(reporte);
    }
}