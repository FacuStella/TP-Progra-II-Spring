package com.sherlockhomes.daos;

import com.sherlockhomes.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoDAO extends JpaRepository<Empleado, Integer> {
    
}