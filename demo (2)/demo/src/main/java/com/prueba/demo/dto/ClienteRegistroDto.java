package com.prueba.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteRegistroDto {
    private String nombres;
    private String apellidos;
    private Long cedula;
    private String contraseña;

}
