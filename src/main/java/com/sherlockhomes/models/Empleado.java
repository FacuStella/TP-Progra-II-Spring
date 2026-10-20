package com.sherlockhomes.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static com.sherlockhomes.TipoUsuario.EMPLEADO;

@Entity
@Table(name = "empleados")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Empleado extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "codigo")
    protected int codigo;

    @Column(name = "especialidad")
    protected String especialidad;

    @ManyToMany(mappedBy = "empleados", fetch = FetchType.LAZY)
    protected List<Zona> zonasAsignadas = new ArrayList<>();

    @OneToMany(mappedBy = "empleadoAsignado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Vehiculo> vehiculosAsignados = new ArrayList<>();

    // Constructor vacío (Obligatorio para JPA)
    public Empleado() {
        super();
    }

    public Empleado(String nombre, int DNI, String direccion, String telefono, String especialidad) {
        super(nombre, DNI, direccion, telefono);
        this.especialidad = especialidad;
    }

    public Empleado(int id, int codigo, String nombre, int DNI, String direccion, String telefono, String especialidad) {
        super(id, nombre, DNI, direccion, telefono, nombre + "Emp", String.format("%04d", DNI % 10000), EMPLEADO);
        this.codigo = codigo;
        this.especialidad = especialidad;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Zona> getZonasAsignadas() {
        return zonasAsignadas;
    }

    public void setZonasAsignadas(List<Zona> zonasAsignadas) {
        this.zonasAsignadas = zonasAsignadas;
    }

    public void asignarZona(Zona zona) {
        if (!this.zonasAsignadas.contains(zona)) {
            this.zonasAsignadas.add(zona);
        }
    }

    public void quitarZona(Zona zona) {
        this.zonasAsignadas.remove(zona);
    }

    public List<Vehiculo> getVehiculosAsignados() {
        return vehiculosAsignados;
    }

    public void setVehiculosAsignados(List<Vehiculo> vehiculosAsignados) {
        this.vehiculosAsignados = vehiculosAsignados;
    }

    public void asignarVehiculo(Vehiculo vehiculo) {
        this.vehiculosAsignados.add(vehiculo);
        vehiculo.setEmpleadoAsignado(this);
    }

    public void quitarVehiculo(Vehiculo vehiculo) {
        this.vehiculosAsignados.remove(vehiculo);
        vehiculo.setEmpleadoAsignado(null);
    }
}