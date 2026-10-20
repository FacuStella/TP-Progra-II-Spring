package com.sherlockhomes.controllers;

import com.sherlockhomes.models.Socio;
import com.sherlockhomes.daos.SocioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socios")
public class SocioController {

    @Autowired
    private SocioDAO socioDAO;

    // Obtener todos los socios
    @GetMapping
    public List<Socio> listarSocios() {
        return socioDAO.findAll();
    }

    // Obtener un socio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Socio> buscarPorId(@PathVariable Integer id) {
        return socioDAO.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Listar los vehículos pertenecientes a un socio (reemplaza case 1)
    @GetMapping("/{id}/vehiculos")
    public ResponseEntity<?> listarVehiculosDelSocio(@PathVariable Integer id) {
        return socioDAO.findById(id)
                .map(socio -> ResponseEntity.ok(socio.getVehiculos()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Listar los garages pertenecientes a un socio (reemplaza case 2)
    @GetMapping("/{id}/garages")
    public ResponseEntity<?> listarGaragesDelSocio(@PathVariable Integer id) {
        return socioDAO.findById(id)
                .map(socio -> ResponseEntity.ok(socio.getGarages()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear socio
    @PostMapping
    public ResponseEntity<Socio> crearSocio(@RequestBody Socio socio) {
        Socio nuevo = socioDAO.save(socio);
        return ResponseEntity.ok(nuevo);
    }

    // Eliminar socio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSocio(@PathVariable Integer id) {
        if (socioDAO.existsById(id)) {
            socioDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}