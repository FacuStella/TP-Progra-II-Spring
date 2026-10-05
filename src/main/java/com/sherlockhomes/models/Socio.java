package com.sherlockhomes.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import static com.sherlockhomes.TipoUsuario.SOCIO;

public class Socio extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L; 

    protected Date fechaIngreso;
    protected ArrayList<Vehiculo> vehiculos;
    protected ArrayList<Garage> garages;
    
    public Socio() {
    }
    
    public Socio(String nombre, int DNI, String direccion, String telefono) {
        super(nombre, DNI, direccion, telefono);
    }

    public Socio(int id, String nombre, int DNI, String direccion, String telefono) {
        super(id, nombre, DNI, direccion, telefono, nombre+"Soc",String.format("%04d", DNI % 10000), SOCIO);
        this.fechaIngreso = Date.from(Instant.now());
        this.vehiculos = new ArrayList();
        this.garages = new ArrayList();
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    public void quitarVehiculo(Vehiculo vehiculo) {
        vehiculos.remove(vehiculo);
    }

    public void comprarGarage(Garage garage) {
        garages.add(garage);
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return (vehiculos.isEmpty()) ? null : new ArrayList<Vehiculo>(vehiculos);
    }

    public ArrayList<Garage> getGarages() {
        return (garages.isEmpty()) ? null : new ArrayList<Garage>(garages);
    }   

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void asignarVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void asignarGarages(ArrayList<Garage> garages) {
        this.garages = garages;
    }
}