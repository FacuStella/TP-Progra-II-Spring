package com.sherlockhomes.models;

import java.io.Serializable;
import java.util.ArrayList;
import static com.sherlockhomes.TipoUsuario.EMPLEADO;

public class Empleado extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L; 
    
    protected int codigo;
    protected String especialidad;
    protected ArrayList<Zona> zonasAsignadas;
    protected ArrayList<Vehiculo> vehiculosAsignados;
    
    public Empleado() {
    }
    
    public Empleado(String nombre, int DNI, String direccion, String telefono, String especialidad) {
        super(nombre, DNI, direccion, telefono);
        this.especialidad = especialidad;
    }

    public Empleado(int id, int codigo, String nombre, int DNI, String direccion, String telefono, String especialidad) {
        super(id, nombre, DNI, direccion, telefono, nombre+"Emp",String.format("%04d", DNI % 10000), EMPLEADO);
        this.codigo = codigo;
        this.especialidad = especialidad;
        this.zonasAsignadas = new ArrayList();
    }

    public int getCodigo() {
        return codigo; 
    }
    
    public String getEspecialidad() {
        return especialidad; 
    }
    
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    public ArrayList<Zona> getZonasAsignadas() {
        return (zonasAsignadas.isEmpty()) ? null : new ArrayList<Zona>(zonasAsignadas); 
    }
    
    public void asignarZona(Zona zona) {
        zonasAsignadas.add(zona);
    }
    
    public void quitarZona(Zona zona) {
        zonasAsignadas.remove(zona);
    }
    
    public void asignarZonas(ArrayList<Zona> zonas) {
        this.zonasAsignadas = zonas;
    }
    
    public ArrayList<Vehiculo> getVehiculosAsignados() {
        return (vehiculosAsignados.isEmpty()) ? null : new ArrayList<Vehiculo>(vehiculosAsignados); 
    }
    
    public void asignarVehiculo(Vehiculo vehiculo) {
        vehiculosAsignados.add(vehiculo);
    }
    
    public void quitarVehiculo(Vehiculo vehiculo) {
        vehiculosAsignados.remove(vehiculo);
    }
    
    public void asignarVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculosAsignados = vehiculos;
    }
    
}