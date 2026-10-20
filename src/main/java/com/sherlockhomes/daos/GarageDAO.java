package com.sherlockhomes.daos;

import com.sherlockhomes.models.Garage;
import com.sherlockhomes.models.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GarageDAO extends JpaRepository<Garage, Integer> {

    // Cambiar findBySocio por findByPropietario
    List<Garage> findByPropietario(Socio propietario);
    
}