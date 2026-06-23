package com.prueba.demo.service;

import com.prueba.demo.dto.PeliculaDto;
import com.prueba.demo.dto.PeliculaRespuestaDto;
import com.prueba.demo.exception.MovieNotFound;
import com.prueba.demo.model.Pelicula;
import com.prueba.demo.repository.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {
    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

    public PeliculaRespuestaDto agregarPelicula(PeliculaDto peliculaDto){

        Pelicula pelicula= new Pelicula();
        pelicula.setNombre(peliculaDto.getNombre());
        pelicula.setDescripción(peliculaDto.getDescripcion());
        pelicula.setGenero(peliculaDto.getGenero());

        Pelicula guardar= peliculaRepositorio.save(pelicula);

        PeliculaRespuestaDto respuesta= new PeliculaRespuestaDto();
        respuesta.setNombre(guardar.getNombre());
        respuesta.setGenero(guardar.getGenero());
        respuesta.setDescripcion(guardar.getDescripción());
        return respuesta;
    }
    public List<Pelicula>listar(){
        return peliculaRepositorio.findAll();
    }
    public List<PeliculaRespuestaDto>encontrarPeliculaPorNombre(String nombre){

       List <Pelicula> pelicula = peliculaRepositorio.findByNombreContaining(nombre);
       if (pelicula.isEmpty()){
           throw new MovieNotFound("pelicula no encontrada");
       }

       List<PeliculaRespuestaDto>respuestaDtos= new ArrayList<>();
       for (Pelicula p:pelicula){
           PeliculaRespuestaDto dto= new PeliculaRespuestaDto();
           dto.setNombre(p.getNombre());
           dto.setGenero(p.getGenero());
           dto.setDescripcion(p.getDescripción());
           dto.setIdPelicula(p.getIdPelicula());
           respuestaDtos.add(dto);

       }
       return respuestaDtos;
    }
    public PeliculaRespuestaDto actualizarPelicula(Long id ,PeliculaDto valorASetear){
     Pelicula pelicula=  peliculaRepositorio.findById(id)
                .orElseThrow(()->new MovieNotFound(
                        "Pelicula no encontrada con id :"+id));

     pelicula.setNombre(valorASetear.getNombre());
     pelicula.setDescripción(valorASetear.getDescripcion() );
     pelicula.setGenero(valorASetear.getGenero());

     Pelicula guardar= peliculaRepositorio.save(pelicula);
     PeliculaRespuestaDto peliculaRespuestaDto = new PeliculaRespuestaDto();
     peliculaRespuestaDto.setNombre(pelicula.getNombre());
     peliculaRespuestaDto.setGenero(pelicula.getGenero());
     peliculaRespuestaDto.setDescripcion(pelicula.getDescripción());
     peliculaRespuestaDto.setIdPelicula(pelicula.getIdPelicula());
     return peliculaRespuestaDto;
    }
    public void eliminarPorId(Long id){
        Pelicula peliculaEliminar=
                peliculaRepositorio.findById(id)
                        .orElseThrow(()->new IllegalArgumentException("" +
                                "no encontrada por id:"+id));

        peliculaRepositorio.delete(peliculaEliminar);
    }
    public Pelicula buscarPorId(Long id ){
         return peliculaRepositorio.findById(id)
                .orElseThrow(()->new MovieNotFound("" +
                        "pelicula  no encontrada por id :"+id));

    }
}
