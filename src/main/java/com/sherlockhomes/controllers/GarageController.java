package com.sherlockhomes.controllers;

import com.sherlockhomes.daos.GarageDAO;
import com.sherlockhomes.daos.SocioDAO;
import com.sherlockhomes.daos.VehiculoDAO;
import com.sherlockhomes.models.Garage;
import com.sherlockhomes.models.Socio;
import com.sherlockhomes.models.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/garages")
public class GarageController {

    @Autowired
    private GarageDAO garageDAO;

    @Autowired
    private SocioDAO socioDAO;

    @Autowired
    private VehiculoDAO vehiculoDAO;

    // 1. LISTAR TODOS LOS GARAGES
    @GetMapping
    public List<Garage> listarTodos() {
        return garageDAO.findAll();
    }

    // 2. BUSCAR POR ID/NUMERO
    @GetMapping("/{id}")
    public ResponseEntity<Garage> buscarPorId(@PathVariable Integer id) {
        return garageDAO.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. REGISTRAR UN GARAGE
    @PostMapping
    public ResponseEntity<Garage> registrarGarage(@RequestBody Garage garage) {
        Garage nuevoGarage = garageDAO.save(garage);
        return ResponseEntity.ok(nuevoGarage);
    }

    // 4. ASIGNAR PROPIETARIO / COMPRAR GARAGE
    @PutMapping("/{garageId}/comprar/{socioId}")
    public ResponseEntity<?> comprarGarage(@PathVariable Integer garageId, @PathVariable Integer socioId) {
        Optional<Garage> garageOpt = garageDAO.findById(garageId);
        Optional<Socio> socioOpt = socioDAO.findById(socioId);

        if (garageOpt.isEmpty() || socioOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Garage o Socio no encontrado");
        }

        Garage garage = garageOpt.get();
        if (garage.getPropietario() != null) {
            return ResponseEntity.badRequest().body("El garage ya tiene un propietario");
        }

        garage.setPropietario(socioOpt.get());
        garageDAO.save(garage);
        return ResponseEntity.ok(garage);
    }

    // 5. ASIGNAR VEHÍCULO A GARAGE
    @PutMapping("/{garageId}/asignar-vehiculo/{patente}")
    public ResponseEntity<?> asignarVehiculo(@PathVariable Integer garageId, @PathVariable String patente) {
        Optional<Garage> garageOpt = garageDAO.findById(garageId);
        Optional<Vehiculo> vehiculoOpt = vehiculoDAO.findById(patente);

        if (garageOpt.isEmpty() || vehiculoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Garage o Vehiculo no encontrado");
        }

        Garage garage = garageOpt.get();
        if (garage.getPropietario() == null) {
            return ResponseEntity.badRequest().body("El garage no tiene propietario asignado");
        }

        garage.setVehiculoOcupante(vehiculoOpt.get());
        garageDAO.save(garage);
        return ResponseEntity.ok(garage);
    }

    // 6. QUITAR VEHÍCULO DE GARAGE
    @PutMapping("/{garageId}/quitar-vehiculo")
    public ResponseEntity<?> quitarVehiculo(@PathVariable Integer garageId) {
        Optional<Garage> garageOpt = garageDAO.findById(garageId);

        if (garageOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Garage garage = garageOpt.get();
        garage.setVehiculoOcupante(null);
        garageDAO.save(garage);
        return ResponseEntity.ok("Vehiculo removido del garage");
    }

    // 7. ELIMINAR GARAGE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGarage(@PathVariable Integer id) {
        if (garageDAO.existsById(id)) {
            garageDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}