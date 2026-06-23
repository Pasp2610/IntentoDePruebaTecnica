package com.prueba.demo.repository;

import com.prueba.demo.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositorio extends JpaRepository<Clientes ,Long> {
   Optional<Clientes>findByCedula(Long cedula);

}
