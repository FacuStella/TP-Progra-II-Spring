package com.sherlockhomes.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static com.sherlockhomes.TipoUsuario.SOCIO;

@Entity
@Table(name = "socios")
@PrimaryKeyJoinColumn(name = "usuario_id") // Enlaza la clave primaria con la tabla usuarios
public class Socio extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_ingreso")
    protected Date fechaIngreso;

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Vehiculo> vehiculos = new ArrayList<>();

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Garage> garages = new ArrayList<>();

    // Constructor vacío obligatorio para JPA
    public Socio() {
        super();
    }

    public Socio(String nombre, int DNI, String direccion, String telefono) {
        super(nombre, DNI, direccion, telefono);
        this.fechaIngreso = Date.from(Instant.now());
    }

    public Socio(int id, String nombre, int DNI, String direccion, String telefono) {
        super(id, nombre, DNI, direccion, telefono, nombre + "Soc", String.format("%04d", DNI % 10000), SOCIO);
        this.fechaIngreso = Date.from(Instant.now());
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
        vehiculo.setPropietario(this);
    }

    public void quitarVehiculo(Vehiculo vehiculo) {
        vehiculos.remove(vehiculo);
        vehiculo.setPropietario(null);
    }

    public void comprarGarage(Garage garage) {
        garages.add(garage);
        garage.setPropietario(this);
    }

    // Getters y Setters
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<Garage> getGarages() {
        return garages;
    }

    public void setGarages(List<Garage> garages) {
        this.garages = garages;
    }
}