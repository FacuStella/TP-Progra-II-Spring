package com.sherlockhomes.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "garages")
public class Garage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_garage")
    protected int numeroGarage;

    @Column(name = "lectura_contador_luz")
    protected double lecturaContadorLuz;

    @Column(name = "mantenimiento_contratado")
    protected boolean mantenimientoContratado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehiculo_patente")
    protected Vehiculo vehiculoOcupante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zona_letra")
    protected Zona zona;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "socio_id")
    protected Socio propietario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_compra")
    protected Date fechaCompra;

    // Constructor vacio obligatorio para JPA
    public Garage() {
    }

    public Garage(Zona zona) {
        this.lecturaContadorLuz = 0;
        this.mantenimientoContratado = false;
        this.zona = zona;
        this.vehiculoOcupante = null;
        this.propietario = null;
        this.fechaCompra = null;
    }

    public Garage(int numero, Zona zona) {
        this.numeroGarage = numero;
        this.lecturaContadorLuz = 0;
        this.mantenimientoContratado = false;
        this.zona = zona;
        this.vehiculoOcupante = null;
        this.propietario = null;
        this.fechaCompra = null;
    }

    // Getters y Setters
    public int getNumeroGarage() {
        return numeroGarage;
    }

    public void setNumeroGarage(int numeroGarage) {
        this.numeroGarage = numeroGarage;
    }

    public double getLecturaContadorLuz() {
        return lecturaContadorLuz;
    }

    public void setLecturaContadorLuz(double lecturaContadorLuz) {
        this.lecturaContadorLuz = lecturaContadorLuz;
    }

    public boolean isMantenimientoContratado() {
        return mantenimientoContratado;
    }

    public void setMantenimientoContratado(boolean mantenimientoContratado) {
        this.mantenimientoContratado = mantenimientoContratado;
    }

    public Vehiculo getVehiculoOcupante() {
        return vehiculoOcupante;
    }

    public void setVehiculoOcupante(Vehiculo vehiculoOcupante) {
        this.vehiculoOcupante = vehiculoOcupante;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Socio getPropietario() {
        return propietario;
    }

    public void setPropietario(Socio propietario) {
        this.propietario = propietario;
        if (propietario != null && this.fechaCompra == null) {
            this.fechaCompra = new Date();
        } else if (propietario == null) {
            this.fechaCompra = null;
        }
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public boolean tienePropietario() {
        return (propietario != null);
    }
}