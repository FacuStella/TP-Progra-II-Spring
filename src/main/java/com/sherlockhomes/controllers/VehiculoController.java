package com.sherlockhomes.controllers;

import com.sherlockhomes.models.Vehiculo;
import com.sherlockhomes.daos.VehiculoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoDAO vehiculoDAO;

    // 1. OBTENER TODOS LOS VEHÍCULOS
    @GetMapping
    public List<Vehiculo> listarTodos() {
        return vehiculoDAO.findAll();
    }

    // 2. BUSCAR POR PATENTE
    @GetMapping("/{patente}")
    public ResponseEntity<Vehiculo> buscarPorPatente(@PathVariable String patente) {
        return vehiculoDAO.findById(patente)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. REGISTRAR UN NUEVO VEHÍCULO
    @PostMapping
    public ResponseEntity<Vehiculo> crearVehiculo(@RequestBody Vehiculo vehiculo) {
        Vehiculo nuevo = vehiculoDAO.save(vehiculo);
        return ResponseEntity.ok(nuevo);
    }

    // 4. ELIMINAR VEHÍCULO
    @DeleteMapping("/{patente}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable String patente) {
        if (vehiculoDAO.existsById(patente)) {
            vehiculoDAO.deleteById(patente);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}