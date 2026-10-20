package com.sherlockhomes.models;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String nombre;
    protected int DNI;
    protected String direccion;
    protected String telefono;

    public Persona() {
    }

    public Persona(String nombre, int DNI, String direccion, String telefono) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getDNI() {
        return DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
