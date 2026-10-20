package com.sherlockhomes.controllers;

import com.sherlockhomes.models.Empleado;
import com.sherlockhomes.daos.EmpleadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoDAO empleadoDAO; // Cambiado a EmpleadoDAO

    // 1. OBTENER TODOS LOS EMPLEADOS
    @GetMapping
    public List<Empleado> listarTodos() {
        return empleadoDAO.findAll();
    }

    // 2. BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarPorId(@PathVariable Integer id) {
        Optional<Empleado> empleado = empleadoDAO.findById(id);
        return empleado.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. REGISTRAR EMPLEADO
    @PostMapping
    public ResponseEntity<Empleado> registrarEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoDAO.save(empleado);
        return ResponseEntity.ok(nuevoEmpleado);
    }

    // 4. MODIFICAR EMPLEADO
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> modificarEmpleado(@PathVariable Integer id, @RequestBody Empleado datosNuevos) {
        return empleadoDAO.findById(id).map(empleado -> {
            empleado.setDireccion(datosNuevos.getDireccion());
            empleado.setTelefono(datosNuevos.getTelefono());
            empleado.setEspecialidad(datosNuevos.getEspecialidad());
            Empleado actualizado = empleadoDAO.save(empleado);
            return ResponseEntity.ok(actualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. ELIMINAR EMPLEADO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Integer id) {
        if (empleadoDAO.existsById(id)) {
            empleadoDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}