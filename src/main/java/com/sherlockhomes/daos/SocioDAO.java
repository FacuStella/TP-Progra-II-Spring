package com.sherlockhomes.daos;

import com.sherlockhomes.models.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocioDAO extends JpaRepository<Socio, Integer> {

    // Método personalizado útil para buscar un socio por DNI en lugar de su ID de tabla
    Optional<Socio> findByDNI(int dni);
}