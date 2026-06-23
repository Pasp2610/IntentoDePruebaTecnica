package com.prueba.demo.service;

import com.prueba.demo.dto.*;
import com.prueba.demo.exception.ClientNotFound;
import com.prueba.demo.exception.MovieNotFound;
import com.prueba.demo.model.Alquiler;
import com.prueba.demo.model.Clientes;
import com.prueba.demo.model.Ejemplares;
import com.prueba.demo.model.Pelicula;
import com.prueba.demo.repository.AlquilerRepository;
import com.prueba.demo.repository.ClienteRepositorio;
import com.prueba.demo.repository.EjemplarRepository;
import com.prueba.demo.repository.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlquilerService {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private EjemplarRepository ejemplarRepositorio;
    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;


    public AlquilerRespuestaDto alquilarPelicula(AlquilerDto dto) {
        // 1. Buscar ejemplares disponibles
       Pelicula pelicula =peliculaRepositorio.findById(dto.getPeliculaId())
               .orElseThrow(()->new MovieNotFound("Pelicula no encontrada"));

       Clientes clientes = clienteRepositorio.findById(dto.getClienteId())
               .orElseThrow(()->new ClientNotFound("Cliente no encontrado"));

       List<Ejemplares>disponibles= ejemplarRepositorio.
               findByPeliculaIdPeliculaAndDisponibleTrue(pelicula);
        if (disponibles.isEmpty()) {

            throw new IllegalArgumentException("Ningun ejemplar disponible");
        }
            // 3. Tomar el primer ejemplar y marcarlo como no disponible
            Ejemplares ejemplar = disponibles.get(0);
             ejemplar.setDisponible(false);
            ejemplarRepositorio.save(ejemplar); // ✅ guardar cambio

        // 4. Crear el alquiler
        Alquiler alquiler = new Alquiler();
        alquiler.setCliente(clientes);                                        // objeto completo
        alquiler.setEjemplar(ejemplar);                                      // objeto completo
        alquiler.setFechaAlquiler(LocalDateTime.now());                      // fecha actual
        alquiler.setFechaDevolucion(LocalDateTime.now().plusDays(7));        // 7 días después
        alquiler.setValorPagado(pelicula.getGenero().getValorAlquiler());

        alquilerRepository.save(alquiler);

        AlquilerRespuestaDto alquilerRespuestaDto = new AlquilerRespuestaDto();
        alquilerRespuestaDto.setIdAlquiler(alquiler.getIdAlquiler());
        alquilerRespuestaDto.setFechaAlquiler(alquiler.getFechaAlquiler());
        alquilerRespuestaDto.setValorPagado(alquiler.getValorPagado());

        // 5. Guardar y retornar
        return alquilerRespuestaDto;
    }
    public AlquilerRespuestaDto devolverPelicula(Long alquilerId) {
        Alquiler base = alquilerRepository.findById(alquilerId)
                .orElseThrow(() -> new IllegalArgumentException("Alquiler no encontrado"));

        base.getEjemplar().setDisponible(true);
        ejemplarRepositorio.save(base.getEjemplar());

        base.setFechaDevolucion(LocalDateTime.now()); // ✅ corregido
        Alquiler guardado = alquilerRepository.save(base); // ✅ guarda el alquiler

        AlquilerRespuestaDto respuesta = new AlquilerRespuestaDto();
        respuesta.setIdAlquiler(guardado.getIdAlquiler());
        respuesta.setNombrePelicula(guardado.getEjemplar().getPelicula().getNombre());
        respuesta.setFechaAlquiler(guardado.getFechaAlquiler());
        respuesta.setFechaDevolucion(guardado.getFechaDevolucion()); // ✅ la que ya tenía
        respuesta.setValorPagado(guardado.getValorPagado());

        return respuesta;


    }
        public List<AlquilerRespuestaDto> historialCliente(Clientes clientes) {
            Clientes buscar = clienteRepositorio.findById(clientes.getClienteId())
                    .orElseThrow(() -> new ClientNotFound("Cliente no encontrado"));

            List<Alquiler> alquiler = alquilerRepository.findByCliente(buscar);

            List<AlquilerRespuestaDto> lista = new ArrayList<>();
            for (Alquiler a : alquiler) {
                AlquilerRespuestaDto alquilerRespuestaDto = new AlquilerRespuestaDto();
                alquilerRespuestaDto.setIdAlquiler(a.getIdAlquiler());
                alquilerRespuestaDto.setNombrePelicula(a.getEjemplar().getPelicula().getNombre());
                alquilerRespuestaDto.setFechaAlquiler(LocalDateTime.now());
                alquilerRespuestaDto.setValorPagado(a.getValorPagado());
                lista.add(alquilerRespuestaDto);


            }
            return lista;

        }
      public List<ReporteAlquiler> generarReporte(){
        List<Object[]>resultado= alquilerRepository.generarReporte();
        return resultado.stream()
                .map(fila-> new ReporteAlquiler(
                        (String) fila[0] ,
                        (Long) fila[1],
                        (Double) fila[2]
                ))
                .collect(Collectors.toList());

        }

    }
