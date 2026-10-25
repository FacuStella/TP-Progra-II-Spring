package com.sherlockhomes.services;

import com.sherlockhomes.dto.VehiculoRequest;
import com.sherlockhomes.exceptions.ConflictoException;
import com.sherlockhomes.exceptions.NoEncontradoException;
import com.sherlockhomes.models.Garage;
import com.sherlockhomes.models.Socio;
import com.sherlockhomes.models.Vehiculo;
import com.sherlockhomes.repositorys.GarageRepository;
import com.sherlockhomes.repositorys.SocioRepository;
import com.sherlockhomes.repositorys.VehiculoRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final SocioRepository socioRepository;
    private final GarageRepository garageRepository;

    @Transactional
    public Vehiculo crearVehiculo(VehiculoRequest r) {
        String patente = normalizar(r.patente());
        if (vehiculoRepository.existsByPatente(patente)) {
            throw new ConflictoException("Ya existe un vehículo con patente " + patente);
        }
        Vehiculo v = new Vehiculo(patente, r.marca(), r.tipo(), r.dimensiones(), buscarSocio(r.dniPropietario()));
        return vehiculoRepository.save(v);
    }

    @Transactional(readOnly = true)
    public Vehiculo buscarPorPatente(String patente) {
        String p = normalizar(patente);
        return vehiculoRepository.findByPatente(p)
                .orElseThrow(() -> new NoEncontradoException("No existe un vehículo con patente " + p));
    }

    @Transactional(readOnly = true)
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }

    @Transactional
    public Vehiculo actualizarVehiculo(String patente, VehiculoRequest r) {
        Vehiculo v = buscarPorPatente(patente);
        String nuevaPatente = normalizar(r.patente());
        if (!v.getPatente().equals(nuevaPatente) && vehiculoRepository.existsByPatente(nuevaPatente)) {
            throw new ConflictoException("Ya existe un vehículo con patente " + nuevaPatente);
        }
        Socio nuevoPropietario = buscarSocio(r.dniPropietario());
        if (!v.getPropietario().getId().equals(nuevoPropietario.getId()) && v.tieneGarageAsignado()) {
            throw new ConflictoException("No se puede cambiar el propietario mientras el vehículo tenga garage asignado");
        }
        v.setPatente(nuevaPatente);
        v.setMarca(r.marca());
        v.setTipo(r.tipo());
        v.setDimensiones(r.dimensiones());
        v.setPropietario(nuevoPropietario);
        return vehiculoRepository.save(v);
    }

    @Transactional
    public void eliminarVehiculo(String patente) {
        vehiculoRepository.delete(buscarPorPatente(patente));
    }

    /**
     * Reglas: el vehículo no puede tener ya un garage, el garage debe estar libre, debe tener
     * propietario y ese propietario debe ser el dueño del vehículo.
     */
    @Transactional
    public Vehiculo asignarGarage(String patente, Integer numeroGarage) {
        Vehiculo v = buscarPorPatente(patente);
        Garage g = garageRepository.findByNumero(numeroGarage)
                .orElseThrow(() -> new NoEncontradoException("No existe el garage " + numeroGarage));

        if (v.tieneGarageAsignado()) {
            throw new ConflictoException("El vehículo ya tiene el garage " + v.getGarageAsignado().getNumero());
        }
        if (vehiculoRepository.existsByGarageAsignadoId(g.getId())) {
            throw new ConflictoException("El garage " + numeroGarage + " ya está ocupado");
        }
        if (!g.tienePropietario()) {
            throw new ConflictoException("El garage " + numeroGarage + " no tiene propietario");
        }
        if (!g.getSocio().getId().equals(v.getPropietario().getId())) {
            throw new ConflictoException("El garage " + numeroGarage + " pertenece a otro socio");
        }
        v.setGarageAsignado(g);
        v.setFechaAsignacion(LocalDate.now());
        return vehiculoRepository.save(v);
    }

    @Transactional
    public Vehiculo quitarGarage(String patente) {
        Vehiculo v = buscarPorPatente(patente);
        if (!v.tieneGarageAsignado()) {
            throw new ConflictoException("El vehículo no tiene garage asignado");
        }
        v.setGarageAsignado(null);
        v.setFechaAsignacion(null);
        return vehiculoRepository.save(v);
    }

    private Socio buscarSocio(Integer dni) {
        return socioRepository.findByDni(dni)
                .orElseThrow(() -> new NoEncontradoException("No existe un socio con DNI " + dni));
    }

    private String normalizar(String patente) {
        return patente == null ? null : patente.trim().toUpperCase();
    }
}
