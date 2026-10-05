package com.sherlockhomes.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Zona implements Serializable {
    private static final long serialVersionUID = 1L; 
    
    protected String letra;
    protected String tipoVehiculos;
    protected int numeroVehiculos;
    protected int profundidadGarage;
    protected int anchoGarage;
    protected ArrayList<Garage> garages;
    protected ArrayList<Empleado> empleados;
    
    public Zona(String letra, String tipoVehiculos, int profundidadGarage, int anchoGarage){
        this.letra = letra;
        this.tipoVehiculos = tipoVehiculos;
        this.profundidadGarage = profundidadGarage;
        this.anchoGarage = anchoGarage;
        this.garages = new ArrayList<Garage>();
        this.empleados = new ArrayList<Empleado>();
    }
    
    public void agregarGarage(Garage garage) {
        garages.add(garage);
    }

    public void asignarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }
    
    public void quitarEmpleado(Empleado empleado) {
        empleados.remove(empleado);
    }

    public ArrayList<Garage> getGarages() {
        return new ArrayList<Garage>(garages);
    }
    
    public ArrayList<Empleado> getEmpleados() {
        return new ArrayList<Empleado>(empleados);
    }

    public String getLetra() {
        return letra;
    }

    public String getTipoVehiculos() {
        return tipoVehiculos;
    }

    public int getNumeroVehiculos() {
        return numeroVehiculos;
    }

    public int getProfundidadGarage() {
        return profundidadGarage;
    }

    public int getAnchoGarage() {
        return anchoGarage;
    }  
}