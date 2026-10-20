package com.sherlockhomes.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "vehiculos")
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "patente", length = 20)
    protected String matricula;

    @Column(name = "marca")
    protected String marca;

    @Column(name = "tipo")
    protected String tipo;

    @Column(name = "dimensiones")
    protected String dimensiones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "socio_id")
    protected Socio propietario;

    @OneToOne(mappedBy = "vehiculoOcupante", cascade = CascadeType.ALL)
    protected Garage garageAsignado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_asignacion")
    protected Date fechaAsignacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empleado_id")
    protected Empleado empleadoAsignado;

    // Constructor vacío obligatorio para JPA / Hibernate
    public Vehiculo() {
    }

    public Vehiculo(String patente, String marca, String tipo, String dimensiones, Socio propietario) {
        this.matricula = patente;
        this.marca = marca;
        this.tipo = tipo;
        this.dimensiones = dimensiones;
        this.propietario = propietario;
    }

    // Getters y Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPatente() {
        return matricula;
    }

    public void setPatente(String patente) {
        this.matricula = patente;
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
        if (garageAsignado != null) {
            this.fechaAsignacion = new Date();
        } else {
            this.fechaAsignacion = null;
        }
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Empleado getEmpleadoAsignado() {
        return empleadoAsignado;
    }

    public void setEmpleadoAsignado(Empleado empleadoAsignado) {
        this.empleadoAsignado = empleadoAsignado;
    }

    // Métodos auxiliares de negocio
    public void asignarGarage(Garage garage) {
        setGarageAsignado(garage);
    }

    public void removerGarage() {
        setGarageAsignado(null);
    }

    public boolean tieneGarageAsignado() {
        return (garageAsignado != null);
    }

    public void asignarEmpleado(Empleado empleadoAsignado) {
        this.empleadoAsignado = empleadoAsignado;
    }

    public void removerEmpleado() {
        this.empleadoAsignado = null;
    }

    public boolean tieneEmpleadoAsignado() {
        return (empleadoAsignado != null);
    }
}
