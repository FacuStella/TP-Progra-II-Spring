package com.sherlockhomes.models;

import java.io.Serializable;
import java.util.Date;

public class Vehiculo implements Serializable {
    private static final long serialVersionUID = 1L; 

    protected String matricula;
    protected String marca;
    protected String tipo;
    protected String dimensiones;
    protected Socio propietario;
    
    protected Garage garageAsignado;
    protected Date fechaAsignacion;
    
    protected Empleado empleadoAsignado;

    public Vehiculo(String patente, String marca, String tipo, String dimensiones, Socio propietario) {
        this.matricula = patente;
        this.marca = marca;
        this.tipo = tipo;
        this.dimensiones = dimensiones;
        this.propietario = propietario;
    }

    Vehiculo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getPatente() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public Socio getPropietario() {
        return propietario;
    }

    public Garage getGarageAsignado() {
        return garageAsignado;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public Empleado getEmpleadoAsignado() {
        return empleadoAsignado;
    }
    
    
    public void asignarGarage(Garage garage) {
        this.garageAsignado = garage;
        this.fechaAsignacion = new Date();
    }

    public void removerGarage() {
        this.garageAsignado = null;
        this.fechaAsignacion = null;
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