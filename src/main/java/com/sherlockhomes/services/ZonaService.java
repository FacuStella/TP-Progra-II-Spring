package com.sherlockhomes.services;

import com.sherlockhomes.dto.ZonaRequest;
import com.sherlockhomes.exceptions.ConflictoException;
import com.sherlockhomes.exceptions.NoEncontradoException;
import com.sherlockhomes.models.Empleado;
import com.sherlockhomes.models.Garage;
import com.sherlockhomes.models.Zona;
import com.sherlockhomes.repositorys.EmpleadoRepository;
import com.sherlockhomes.repositorys.GarageRepository;
import com.sherlockhomes.repositorys.ZonaRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ZonaService {

    private final ZonaRepository zonaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final GarageRepository garageRepository;

    @Transactional
    public Zona crearZona(ZonaRequest r) {
        String letra = normalizar(r.letra());
        if (zonaRepository.existsByLetra(letra)) {
            throw new ConflictoException("Ya existe la zona " + letra);
        }
        return zonaRepository.save(new Zona(letra, r.tipoVehiculos(), r.profundidadGarage(), r.anchoGarage()));
    }

    @Transactional(readOnly = true)
    public Zona buscarPorLetra(String letra) {
        String l = normalizar(letra);
        return zonaRepository.findByLetra(l)
                .orElseThrow(() -> new NoEncontradoException("No existe la zona " + l));
    }

    @Transactional(readOnly = true)
    public List<Zona> listarZonas() {
        return zonaRepository.findAll();
    }

    @Transactional
    public Zona actualizarZona(String letra, ZonaRequest r) {
        Zona zona = buscarPorLetra(letra);
        String nuevaLetra = normalizar(r.letra());
        if (!zona.getLetra().equals(nuevaLetra) && zonaRepository.existsByLetra(nuevaLetra)) {
            throw new ConflictoException("Ya existe la zona " + nuevaLetra);
        }
        zona.setLetra(nuevaLetra);
        zona.setTipoVehiculos(r.tipoVehiculos());
        zona.setProfundidadGarage(r.profundidadGarage());
        zona.setAnchoGarage(r.anchoGarage());
        return zonaRepository.save(zona);
    }

    @Transactional
    public void eliminarZona(String letra) {
        Zona zona = buscarPorLetra(letra);
        if (garageRepository.existsByZonaId(zona.getId())) {
            throw new ConflictoException("No se puede eliminar la zona: todavía tiene garages");
        }
        zonaRepository.delete(zona);
    }

    @Transactional
    public Zona asignarEmpleado(String letra, Integer dniEmpleado) {
        Zona zona = buscarPorLetra(letra);
        Empleado empleado = buscarEmpleado(dniEmpleado);
        zona.asignarEmpleado(empleado);
        return zonaRepository.save(zona);
    }

    @Transactional
    public Zona quitarEmpleado(String letra, Integer dniEmpleado) {
        Zona zona = buscarPorLetra(letra);
        Empleado empleado = buscarEmpleado(dniEmpleado);
        zona.quitarEmpleado(empleado);
        return zonaRepository.save(zona);
    }

    @Transactional(readOnly = true)
    public List<Garage> listarGarages(String letra) {
        return garageRepository.findByZonaLetra(buscarPorLetra(letra).getLetra());
    }

    @Transactional(readOnly = true)
    public List<Empleado> listarEmpleados(String letra) {
        return new ArrayList<>(buscarPorLetra(letra).getEmpleados());
    }

    private Empleado buscarEmpleado(Integer dni) {
        return empleadoRepository.findByDni(dni)
                .orElseThrow(() -> new NoEncontradoException("No existe un empleado con DNI " + dni));
    }

    private String normalizar(String letra) {
        return letra == null ? null : letra.trim().toUpperCase();
    }
}
