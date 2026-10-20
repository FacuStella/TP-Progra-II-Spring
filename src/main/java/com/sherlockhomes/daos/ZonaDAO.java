package com.sherlockhomes.daos;

import com.sherlockhomes.models.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaDAO extends JpaRepository<Zona, String> {
}