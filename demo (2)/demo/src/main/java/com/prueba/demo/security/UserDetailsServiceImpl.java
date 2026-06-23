package com.prueba.demo.security;

import com.prueba.demo.exception.ClientNotFound;
import com.prueba.demo.model.Clientes;
import com.prueba.demo.repository.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Override
    public UserDetails loadUserByUsername(String cedula){

        Clientes clientes= clienteRepositorio.findByCedula(Long.valueOf(cedula))
                .orElseThrow(()->new ClientNotFound("Cliente no encontrado"));

        return User.builder()
                .username(cedula)
                .password(clientes.getContraseña())
                .roles("user")
                .build();

    }
}
