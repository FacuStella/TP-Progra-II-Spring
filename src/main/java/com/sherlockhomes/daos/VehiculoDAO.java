package com.sherlockhomes.daos;

import com.sherlockhomes.models.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiculoDAO extends JpaRepository<Vehiculo, String> {

    @Query("SELECT v FROM Vehiculo v WHERE v.matricula = :patente")
    Optional<Vehiculo> findByPatente(@Param("patente") String patente);

}