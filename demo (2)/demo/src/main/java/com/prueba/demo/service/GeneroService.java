package com.prueba.demo.service;

import com.prueba.demo.model.Genero;
import com.prueba.demo.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    public Genero agregarGenero(Genero genero){
        return generoRepository.save(genero);
    }
    public List<Genero>listarGeneros(){
        return generoRepository.findAll();
    }
    public Genero actulizarValorAlquiler(Genero generoValor,Double nuevoValor){
        Genero genero = generoRepository.findById(generoValor.getGeneroId())
                .orElseThrow(()->new IllegalArgumentException
                        ("Genero no encontrado con id"+generoValor.getGeneroId()));
        genero.setValorAlquiler(nuevoValor);
        return generoRepository.save(genero);


    }
}
