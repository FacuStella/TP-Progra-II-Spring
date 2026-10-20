package com.sherlockhomes.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zonas")
public class Zona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "letra", length = 10)
    protected String letra;

    @Column(name = "tipo_vehiculos")
    protected String tipoVehiculos;

    @Column(name = "numero_vehiculos")
    protected int numeroVehiculos;

    @Column(name = "profundidad_garage")
    protected int profundidadGarage;

    @Column(name = "ancho_garage")
    protected int anchoGarage;

    @OneToMany(mappedBy = "zona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Garage> garages = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "zona_empleado",
        joinColumns = @JoinColumn(name = "zona_letra"),
        inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
    protected List<Empleado> empleados = new ArrayList<>();

    // Constructor vacío obligatorio para JPA / Hibernate
    public Zona() {
    }

    public Zona(String letra, String tipoVehiculos, int profundidadGarage, int anchoGarage) {
        this.letra = letra;
        this.tipoVehiculos = tipoVehiculos;
        this.profundidadGarage = profundidadGarage;
        this.anchoGarage = anchoGarage;
    }

    // Métodos de negocio sincronizados bidireccionalmente
    public void agregarGarage(Garage garage) {
        garages.add(garage);
        garage.setZona(this);
    }

    public void asignarEmpleado(Empleado empleado) {
        if (!empleados.contains(empleado)) {
            empleados.add(empleado);
        }
    }

    public void quitarEmpleado(Empleado empleado) {
        empleados.remove(empleado);
    }

    // Getters y Setters
    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getTipoVehiculos() {
        return tipoVehiculos;
    }

    public void setTipoVehiculos(String tipoVehiculos) {
        this.tipoVehiculos = tipoVehiculos;
    }

    public int getNumeroVehiculos() {
        return numeroVehiculos;
    }

    public void setNumeroVehiculos(int numeroVehiculos) {
        this.numeroVehiculos = numeroVehiculos;
    }

    public int getProfundidadGarage() {
        return profundidadGarage;
    }

    public void setProfundidadGarage(int profundidadGarage) {
        this.profundidadGarage = profundidadGarage;
    }

    public int getAnchoGarage() {
        return anchoGarage;
    }

    public void setAnchoGarage(int anchoGarage) {
        this.anchoGarage = anchoGarage;
    }

    public List<Garage> getGarages() {
        return garages;
    }

    public void setGarages(List<Garage> garages) {
        this.garages = garages;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
}