package com.prueba.demo.service;

import com.prueba.demo.dto.ClienteRegistroDto;
import com.prueba.demo.dto.ClienteRespuestaDto;
import com.prueba.demo.dto.LoginDto;
import com.prueba.demo.exception.ClientNotFound;
import com.prueba.demo.model.Clientes;
import com.prueba.demo.repository.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ClienteService {
    @Autowired
    ClienteRepositorio clienteRepositorio;

public ClienteRespuestaDto añadirCliente(ClienteRegistroDto dto){
    if (clienteRepositorio.findById(dto.getCedula()).isPresent()){
        throw new IllegalArgumentException("Ya existe un cliente con esa cedula");
    }
    Clientes clientes = new Clientes();
    clientes.setNombres(dto.getNombres());
    clientes.setApellidos(dto.getApellidos());
    clientes.setCedula(dto.getCedula());
    clientes.setFechaDeCreacion(LocalDateTime.now());
    clientes.setContraseña(dto.getContraseña());

    Clientes guardar = clienteRepositorio.save(clientes);

    ClienteRespuestaDto respuesta= new ClienteRespuestaDto();
    respuesta.setNombres(guardar.getNombres());
    respuesta.setApellidos(guardar.getApellidos());
    respuesta.setCedula(guardar.getCedula());
    return respuesta;

}
    public List<Clientes> mostrarClientes() {
        return clienteRepositorio.findAll();
    }

    public Clientes encontrarClientePorId(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Id inválido");
        }
        return clienteRepositorio.findById(id)
                .orElseThrow(() -> new ClientNotFound(
                        "Cliente no encontrado con id: " + id));
    }

    public ClienteRespuestaDto login(LoginDto dto) {
        Clientes encontrar= clienteRepositorio.findByCedula(dto.getCedula())
                .orElseThrow(()->new IllegalArgumentException
                        ("Usuario no encontrado con cedula :"+ dto.getCedula()));


        if (!encontrar.getContraseña().equals(dto.getContraseña())) {
            throw new IllegalArgumentException("Usuario incorrecto");
        }

        ClienteRespuestaDto respuesta= new ClienteRespuestaDto();
        respuesta.setCedula(encontrar.getCedula());
        respuesta.setNombres(encontrar.getNombres());
        respuesta.setApellidos(encontrar.getApellidos());
        return respuesta;
}
}

