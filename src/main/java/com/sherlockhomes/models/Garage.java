package com.sherlockhomes.models;

import com.sherlockhomes.models.Socio;
import java.io.Serializable;
import java.util.Date;

public class Garage implements Serializable {
    private static final long serialVersionUID = 1L; 
    
    protected int numeroGarage;
    protected double lecturaContadorLuz;
    protected boolean mantenimientoContratado;
    protected Vehiculo vehiculoOcupante;
    protected Zona zona;
    
    protected Socio propietario;
    protected Date fechaCompra;
    
    public Garage(Zona zona) {
        this.numeroGarage = 0;
        this.lecturaContadorLuz = 0;
        this.mantenimientoContratado = false;
        this.zona = zona;
        this.vehiculoOcupante = null;
        this.propietario = null;
        this.fechaCompra = null;
    }
    
    public Garage(int numero,Zona zona) {
        this.numeroGarage = numero;
        this.lecturaContadorLuz = 0;
        this.mantenimientoContratado = false;
        this.zona = zona;
        this.vehiculoOcupante = null;
        this.propietario = null;
        this.fechaCompra = null;
    }
    
    public int getNumeroGarage() {
        return numeroGarage;
    }

    public double getLecturaContadorLuz() {
        return lecturaContadorLuz;
    }
    
    public Vehiculo getVehiculoOcupante() {
        return vehiculoOcupante;
    }

    public Zona getZona() {
        return zona;
    }

    public Socio getPropietario() {
        return propietario;
    }
    
    public Date getFechaCompra() {
        return fechaCompra;
    }
    
    public boolean isMantenimientoContratado() {
        return mantenimientoContratado;
    }
    
    public boolean tienePropietario() {
        return (propietario != null);
    }
    
     public void asignarPropietario(Socio socio) {
        this.propietario = socio;
        this.fechaCompra = new Date();
    }

    public void removerPropietario() {
        this.propietario = null;
        this.fechaCompra = null;
    }
    
    public void asignarVehiculo(Vehiculo vehiculo) {
        this.vehiculoOcupante = vehiculo;
    }

    public void removerVehiculo() {
        this.vehiculoOcupante = null;
    }

}