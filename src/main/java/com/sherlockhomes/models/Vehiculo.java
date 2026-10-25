package com.sherlockhomes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    protected String patente;

    @NotBlank
    @Column(nullable = false)
    protected String marca;

    @NotBlank
    @Column(nullable = false)
    protected String tipo;

    @NotBlank
    @Column(nullable = false)
    protected String dimensiones;

    @ManyToOne(optional = false)
    @JoinColumn(name = "socio_id", nullable = false)
    protected Socio propietario;

    @OneToOne
    @JoinColumn(name = "garage_id", unique = true)
    protected Garage garageAsignado;

    protected LocalDate fechaAsignacion;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    protected Empleado empleadoAsignado;

    public Vehiculo() {
    }

    public Vehiculo(String patente, String marca, String tipo, String dimensiones, Socio propietario) {
        this.patente = patente;
        this.marca = marca;
        this.tipo = tipo;
        this.dimensiones = dimensiones;
        this.propietario = propietario;
    }

    public boolean tieneGarageAsignado() {
        return garageAsignado != null;
    }

    public boolean tieneEmpleadoAsignado() {
        return empleadoAsignado != null;
    }

    public Long getId() {
        return id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public Socio getPropietario() {
        return propietario;
    }

    public void setPropietario(Socio propietario) {
        this.propietario = propietario;
    }

    public Garage getGarageAsignado() {
        return garageAsignado;
    }

    public void setGarageAsignado(Garage garageAsignado) {
        this.garageAsignado = garageAsignado;
    }

    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Empleado getEmpleadoAsignado() {
        return empleadoAsignado;
    }

    public void setEmpleadoAsignado(Empleado empleadoAsignado) {
        this.empleadoAsignado = empleadoAsignado;
    }
    
    
}
