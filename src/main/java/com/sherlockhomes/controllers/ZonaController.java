package com.sherlockhomes.controllers;

import com.sherlockhomes.models.Zona;
import com.sherlockhomes.daos.ZonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zonas")
public class ZonaController {

    @Autowired
    private ZonaDAO zonaDAO;

    // 1. OBTENER TODAS LAS ZONAS
    @GetMapping
    public List<Zona> listarTodas() {
        return zonaDAO.findAll();
    }

    // 2. BUSCAR POR LETRA
    @GetMapping("/{letra}")
    public ResponseEntity<Zona> buscarPorLetra(@PathVariable String letra) {
        return zonaDAO.findById(letra)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. REGISTRAR ZONA
    @PostMapping
    public ResponseEntity<Zona> crearZona(@RequestBody Zona zona) {
        Zona nueva = zonaDAO.save(zona);
        return ResponseEntity.ok(nueva);
    }

    // 4. LISTAR GARAGES DE UNA ZONA
    @GetMapping("/{letra}/garages")
    public ResponseEntity<?> listarGaragesDeZona(@PathVariable String letra) {
        return zonaDAO.findById(letra)
                .map(zona -> ResponseEntity.ok(zona.getGarages()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. ELIMINAR ZONA
    @DeleteMapping("/{letra}")
    public ResponseEntity<Void> eliminarZona(@PathVariable String letra) {
        if (zonaDAO.existsById(letra)) {
            zonaDAO.deleteById(letra);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}