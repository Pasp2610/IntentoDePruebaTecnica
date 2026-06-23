package com.prueba.demo.repository;

import com.prueba.demo.dto.ReporteAlquiler;
import com.prueba.demo.model.Alquiler;
import com.prueba.demo.model.Clientes;
import com.prueba.demo.model.Pelicula;
import com.prueba.demo.service.ClienteService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    List<Alquiler> findByCliente(Clientes cliente); // ✅ singular

    @Query("SELECT a.ejemplar.pelicula.nombre, " +
            "COUNT(a), " +
            "SUM(a.valorPagado) " +
            "FROM Alquiler a " +
            "GROUP BY a.ejemplar.pelicula.nombre " +
            "ORDER BY SUM(a.valorPagado) DESC")
    List<Object[]> generarReporte();
}


